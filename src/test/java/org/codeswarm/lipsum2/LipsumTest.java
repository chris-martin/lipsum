package org.codeswarm.lipsum2;

import java.util.Arrays;
import java.util.Random;

import junit.framework.Assert;
import org.testng.annotations.Test;

public class LipsumTest {

  @Test
  public void testLoremSize() throws Exception {
    Assert.assertEquals(Lipsum.lorem().paragraph(0), Lipsum.lorem().paragraph(150));
  }

  @Test
  public void testLorem42() throws Exception {
    Assert.assertTrue(Lipsum.lorem().paragraph(42).length() != 0);
  }

  @Test
  public void testLoremNegative42() throws Exception {
    Assert.assertTrue(Lipsum.lorem().paragraph(-42).length() != 0);
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

}
