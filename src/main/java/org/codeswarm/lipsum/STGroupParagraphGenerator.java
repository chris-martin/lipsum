package org.codeswarm.lipsum;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

/**
 * A {@link org.codeswarm.lipsum.Lipsum.ParagraphGenerator} implementation backed by an {@link STGroup}.
 */
class STGroupParagraphGenerator implements Lipsum.ParagraphGenerator {

  interface TemplateNames {
    int getMinIndex();
    int getMaxIndex();
    String getTemplateName(int index);
  }

  private final STGroup stg;
  private final TemplateNames templateNames;

  STGroupParagraphGenerator(STGroup stg, TemplateNames templateNames) {
    this.stg = stg;
    this.templateNames = templateNames;
  }

  @Override
  public String paragraph(long key) {
    int index = templateNames.getMinIndex() + Lipsum.remainder(
      Math.abs(key - templateNames.getMinIndex()),
      templateNames.getMaxIndex());
    String tplName = templateNames.getTemplateName(index);
    ST tpl = stg.getInstanceOf(tplName);
    return tpl.render();
  }

}
