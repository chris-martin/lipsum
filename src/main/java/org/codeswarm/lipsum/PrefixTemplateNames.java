package org.codeswarm.lipsum;

class PrefixTemplateNames implements STGroupParagraphGenerator.TemplateNames {

  private final String prefix;
  private final int minIndex;
  private final int maxIndex;

  PrefixTemplateNames(String prefix, int minIndex, int maxIndex) {
    if (minIndex < 0) throw new IllegalArgumentException("minIndex must be >= 0");
    if (minIndex > maxIndex) throw new IllegalArgumentException("maxIndex >= minIndex");
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
