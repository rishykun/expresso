// Generated from Expression.g4 by ANTLR 4.5

package expresso.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ExpressionParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ExpressionParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#build}.
	 * @param ctx the parse tree
	 */
	void enterBuild(ExpressionParser.BuildContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#build}.
	 * @param ctx the parse tree
	 */
	void exitBuild(ExpressionParser.BuildContext ctx);
}