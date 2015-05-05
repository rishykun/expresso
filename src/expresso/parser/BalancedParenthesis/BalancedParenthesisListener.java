// Generated from BalancedParenthesis.g4 by ANTLR 4.5

package expresso.parser.BalancedParenthesis;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BalancedParenthesisParser}.
 */
public interface BalancedParenthesisListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BalancedParenthesisParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(BalancedParenthesisParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link BalancedParenthesisParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(BalancedParenthesisParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link BalancedParenthesisParser#build}.
	 * @param ctx the parse tree
	 */
	void enterBuild(BalancedParenthesisParser.BuildContext ctx);
	/**
	 * Exit a parse tree produced by {@link BalancedParenthesisParser#build}.
	 * @param ctx the parse tree
	 */
	void exitBuild(BalancedParenthesisParser.BuildContext ctx);
}