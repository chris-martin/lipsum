package org.codeswarm.lipsum;

import com.google.common.base.Preconditions;

class PrefixTemplateNames implements STGroupParagraphGenerator.TemplateNames {

  private final String prefix;
  private final int minIndex;
  private final int maxIndex;

  PrefixTemplateNames(String prefix, int minIndex, int maxIndex) {
    Preconditions.checkArgument(minIndex >= 0);
    Preconditions.checkArgument(minIndex <= maxIndex);
    this.prefix = prefix;
    this.minIndex = minIndex;
    this.maxIndex = maxIndex;
  }

  @Override
  public int getMinIndex() {
    return minIndex;
  }

  @Override
  public int getMaxIndex() {
    return maxIndex;
  }

  @Override
  public String getTemplateName(int index) {
    return prefix + Integer.toString(index);
  }

}
