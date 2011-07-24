package org.codeswarm.lipsum;

import junit.framework.Assert;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;

public class LipsumTest {

  @Test
  public void testLorem42() throws Exception {
    Assert.assertTrue(Lipsum.lorem().paragraph(42).length() != 0);
  }

  @Test
  public void testLoremWithReallyHighKey() throws Exception {
    Assert.assertTrue(Lipsum.lorem().paragraph(7l * Integer.MAX_VALUE).length() != 0);
  }

  @Test
  public void testBacon42() throws Exception {
    Assert.assertTrue(Lipsum.bacon().paragraph(42).length() != 0);
  }

  @Test
  public void testLorizzle42() throws Exception {
    Assert.assertTrue(Lipsum.lorizzle().paragraph(new Random().nextLong()).length() != 0);
  }

  @Test
  public void testStringTemplateImport() throws Exception {
    STGroup stg = new STGroupFile("org/codeswarm/lipsum/test1.stg");
    Assert.assertTrue(stg.getInstanceOf("InsightfulProse").render().length() != 0);
  }

  @Test
  public void testList0() throws Exception {
    Lipsum.ParagraphGenerator paragraphGenerator = Lipsum.listParagraphGenerator(Arrays.asList("a", "b", "c", "d"));
    Assert.assertEquals("a", paragraphGenerator.paragraph(0));
  }

  @Test
  public void testList1() throws Exception {
    Lipsum.ParagraphGenerator paragraphGenerator = Lipsum.listParagraphGenerator(Arrays.asList("a", "b", "c", "d"));
    Assert.assertEquals("c", paragraphGenerator.paragraph(83534124890242l));
  }

  @Test
  public void testRemainder1() throws Exception {
    Assert.assertEquals(2, Lipsum.remainder(43525346243662l, 4));
  }

  @Test
  public void testRemainder2() throws Exception {
    Assert.assertEquals(0, Lipsum.remainder(5 * (long) Integer.MAX_VALUE, 5));
  }

  @Test
  public void testAttributeRenderer() throws Exception {
    Lipsum.AttributeRenderer renderer = Lipsum.attributeRenderer();
    renderer.register("bar", Lipsum.listParagraphGenerator(Arrays.asList("zero", "one", "two")));
    renderer.register("bar2", Lipsum.listParagraphGenerator(Arrays.asList("alpha", "beta", "charlie")));
    STGroup stg = new STGroupFile("org/codeswarm/lipsum/test2.stg");
    stg.registerRenderer(Integer.class, renderer);
    ST st = stg.getInstanceOf("foo");
    st.add("x", 7);
    st.add("y", 8);
    Assert.assertEquals("one-charlie", st.render());
  }

}
