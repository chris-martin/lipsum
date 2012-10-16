package org.codeswarm.lipsum2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
     * A standard "lorem ipsum" with 150 paragraphs.
     *
     * <p>From the lipsum LaTeX package
     * <tt>http://carroll.aset.psu.edu/pub/CTAN/macros/latex/contrib/lipsum/lipsum.dtx</tt>.</p>
     */
    public static ParagraphGenerator lorem() {
        return listParagraphGenerator(readLines(Lipsum.class.getResourceAsStream("lorem.txt")));
    }

    /**
     * A "meaty" variant of lorem ipsum" with 150 paragraphs.
     *
     * <p>Generated with the "meat and filler" option at <tt>http://baconipsum.com/</tt>.</p>
     */
    public static ParagraphGenerator bacon() {
        return listParagraphGenerator(readLines(Lipsum.class.getResourceAsStream("bacon.txt")));
    }

    /**
     * Gangsta lorem ipsum with 150 paragraphs.
     *
     * <p>Generated at <tt>http://www.lorizzle.nl/</tt>.</p>
     */
    public static ParagraphGenerator lorizzle() {
        return listParagraphGenerator(readLines(Lipsum.class.getResourceAsStream("lorizzle.txt")));
    }

    /**
     * Generate paragraphs by invoking {@link Object#toString()}
     * on each item in {@code list}.
     */
    public static ParagraphGenerator listParagraphGenerator(List<?> list) {
        return new ListParagraphGenerator(list);
    }

    static int remainder(long numerator, int denominator) {
        return (int) ((numerator % (long) denominator) + denominator) % denominator;
    }

    static List<String> readLines(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> list = new ArrayList<String>();
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.length() != 0) {
                    list.add(line);
                }
                line = reader.readLine();
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
