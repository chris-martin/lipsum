package org.codeswarm.lipsum;

import com.google.common.collect.MapMaker;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;
import java.util.Map;

/**

<p>Utility methods for providers of filler text (lorem ipsum
and the like).</p>

<p>To select paragraph 42 of Lorem Ipsum:</p>

<blockquote><pre>{@code
String paragraph = Lipsum.lorem().paragraph(42);
}</pre></blockquote>

<p>The generators provided by this class are all deterministic.
But if you need randomness, it's easy to do it yourself:</p>

<blockquote><pre>{@code
Lipsum.lorem().paragraph(new Random().nextLong());
}</pre></blockquote>

<p>If a paragraph generator is backed by
<a href="http://www.stringtemplate.org/">StringTemplate</a>,
its group file may also be referenced directly:</p>

<blockquote><pre>{@code
import "org/codeswarm/lipsum/lorem.stg"
paragraph() ::= "<LoremIpsum42()>"
}</pre></blockquote>

*/
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
   * {@code LorizzleIpsumX}, where {@code X} is an integer between 1 and 150.
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

  /** Soft cache of {@link STGroupParagraphGenerator}s. */
  private static final Map<String, ParagraphGenerator> STG_CACHE =
    new MapMaker().softValues().initialCapacity(3).makeMap();

  private static ParagraphGenerator _stParagraphGenerator(
      String name, String prefix, int maxIndex) {

    ParagraphGenerator paragraphGenerator = STG_CACHE.get(name);
    if (paragraphGenerator == null) {
      STGroup stg = new STGroupFile("org/codeswarm/lipsum/" + name + ".stg");
      paragraphGenerator = stParagraphGenerator(stg, prefix, maxIndex);
      STG_CACHE.put(name, paragraphGenerator);
    }
    return paragraphGenerator;
  }

  static int remainder(long numerator, int denominator) {
    return (int) (numerator % (long) denominator);
  }

  public static AttributeRenderer attributeRenderer() {
    return new AttributeRendererImpl();
  }

}
