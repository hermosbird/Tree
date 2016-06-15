import static org.junit.Assert.*;

import org.junit.Test;

/*
 * A unit test for ExpressionTree
 */


public class ExpressionTreeTest {

  @Test
  public void testPrefixWithZeroOrOneOperands() {
    ExpressionTree et0 = new ExpressionTree("");
    assertEquals("", et0.inFix());
    ExpressionTree et1 = new ExpressionTree("99");
    assertEquals("99", et1.inFix());
  }

  @Test
  public void testPrefixWithZeroMoreThanOneOperand() {
    ExpressionTree et3 = new ExpressionTree("- 500 900");
    assertEquals("500 - 900", et3.inFix());
    ExpressionTree et7 = new ExpressionTree("- * 99 88 / 55 44");
    assertEquals("99 * 88 - 55 / 44", et7.inFix());
  }

  @Test
  public void testValueOf() {
    ExpressionTree et0 = new ExpressionTree("");
    assertEquals(0, et0.valueOf());
    ExpressionTree et1 = new ExpressionTree("99");
    assertEquals(99, et1.valueOf());
    ExpressionTree et2 = new ExpressionTree("- 500 900");
    assertEquals(-400, et2.valueOf());

    ExpressionTree et3 = new ExpressionTree("+ 5 9");
    assertEquals(14, et3.valueOf());

    ExpressionTree et4 = new ExpressionTree("* 5 -9");
    assertEquals(-45, et4.valueOf());

    ExpressionTree et5 = new ExpressionTree("/ 89 25 ");
    assertEquals(3, et5.valueOf());

    ExpressionTree et6 = new ExpressionTree("% 89 25 ");
    assertEquals(14, et6.valueOf());

    ExpressionTree et7 = new ExpressionTree("- * / 4 3 5 6");
    assertEquals(-1, et7.valueOf());

    ExpressionTree et8 = new ExpressionTree("- + * 12 11 10 / %  9 8 7");
    assertEquals("12 * 11 + 10 - 9 % 8 / 7", et8.inFix());
    //           -
    //        /     \
    //      +         /
    //    /   \     /   \
    //   *    10    %    7
    //  / \        / \
    // 12 11      9   8
    //
    assertEquals(142, et8.valueOf());

  }

  @Test
  public void testHeight() {
    ExpressionTree et0 = new ExpressionTree("");
    assertEquals(-1, et0.height());

    ExpressionTree et1 = new ExpressionTree("79");
    assertEquals(0, et1.height());

    ExpressionTree et3 = new ExpressionTree("+ 7 9");
    assertEquals(1, et3.height());
    
    ExpressionTree et5 = new ExpressionTree(" - + 4 2 99");
    assertEquals(2, et5.height());

    ExpressionTree et7 = new ExpressionTree("+ + + 8 5 4 3");
    assertEquals(3, et7.height());
  }
}