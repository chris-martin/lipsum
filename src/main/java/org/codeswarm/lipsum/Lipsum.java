package org.codeswarm.lipsum;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;

public final class Lipsum {

  private Lipsum() { }

  /**
   * Provides filler text.
   */
  public static interface ParagraphGenerator {

    /**
     * <p>Returns a deterministic paragraph of filler text
     * based on the provided {@code key}.</p>
     *
     * <p>An implementation backed by a String array {@code xs}
     * could return {@code xs[Math.abs(key) % xs.length]}.
     * A more sophisticated implementation could use {@code key}
     * as the seed to a random word generator.</p>
     */
    String paragraph(long key);

  }

  /**
   * A StringTemplate {@link org.stringtemplate.v4.AttributeRenderer}
   * which renders {@link Integer}s and {@link Long}s by mapping them with
   * registered {@link org.codeswarm.lipsum.Lipsum.ParagraphGenerator}s.
   */
  public static interface AttributeRenderer
      extends org.stringtemplate.v4.AttributeRenderer {

    void register(String format, ParagraphGenerator paragraphGenerator);

  }

  /**
   * A standard "lorem ipsum" with 150 paragraphs.
   *
   * This content can also be used in StringTemplate be importing
   * {@code org/codeswarm/lipsum/lorem.stg} and invoking templates named
   * {@code LoremIpsumX}, where {@code X} is an integer between 1 and 150.
   */
  public static ParagraphGenerator lorem() {
    return _stParagraphGenerator("lorem", "LoremIpsum", 150);
  }

  /**
   * A "meaty" variant of lorem ipsum" with 150 paragraphs.
   *
   * This content can also be used in StringTemplate be importing
   * {@code org/codeswarm/lipsum/bacon.stg} and invoking templates named
   * {@code BaconIpsumX}, where {@code X} is an integer between 1 and 150.
   */
  public static ParagraphGenerator bacon() {
    return _stParagraphGenerator("bacon", "BaconIpsum", 150);
  }

  /**
   * Gangsta lorem ipsum with 150 paragraphs.
   *
   * This content can also be used in StringTemplate be importing
   * {@code org/codeswarm/lipsum/lorizzle.stg} and invoking templates named
   * {@code BaconIpsumX}, where {@code X} is an integer between 1 and 150.
   */
  public static ParagraphGenerator lorizzle() {
    return _stParagraphGenerator("lorizzle", "LorizzleIpsum", 150);
  }

  /**
   * Generate paragraphs by invoking {@link Object#toString()}
   * on each item in {@code list}.
   */
  public static ParagraphGenerator listParagraphGenerator(List<?> list) {
    return new ListParagraphGenerator(list);
  }

  public static ParagraphGenerator stParagraphGenerator(
      STGroup stg, STGroupParagraphGenerator.TemplateNames templateNames) {
    return new STGroupParagraphGenerator(stg, templateNames);
  }

  public static ParagraphGenerator stParagraphGenerator(
      STGroup stg, String prefix, int minIndex, int maxIndex) {
    return stParagraphGenerator(stg, new PrefixTemplateNames(prefix, minIndex, maxIndex));
  }

  public static ParagraphGenerator stParagraphGenerator(
      STGroup stg, String prefix, int maxIndex) {
    return stParagraphGenerator(stg, prefix, 1, maxIndex);
  }

  public static ParagraphGenerator stParagraphGenerator(
      STGroup stg, int maxIndex) {
    return stParagraphGenerator(stg, "lipsum", maxIndex);
  }

  private static ParagraphGenerator _stParagraphGenerator(
      String name, String prefix, int maxIndex) {
    STGroup stg = new STGroupFile("org/codeswarm/lipsum/" + name + ".stg");
    return stParagraphGenerator(stg, prefix, maxIndex);
  }

  static int remainder(long numerator, int denominator) {
    return (int) (numerator % (long) denominator);
  }

  public static AttributeRenderer attributeRenderer() {
    return new AttributeRendererImpl();
  }

}
