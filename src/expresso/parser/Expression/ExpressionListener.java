// Generated from Expression.g4 by ANTLR 4.5

package expresso.parser.Expression;

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
	 * Enter a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ExpressionParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ExpressionParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#tail}.
	 * @param ctx the parse tree
	 */
	void enterTail(ExpressionParser.TailContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#tail}.
	 * @param ctx the parse tree
	 */
	void exitTail(ExpressionParser.TailContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#loner}.
	 * @param ctx the parse tree
	 */
	void enterLoner(ExpressionParser.LonerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#loner}.
	 * @param ctx the parse tree
	 */
	void exitLoner(ExpressionParser.LonerContext ctx);
}