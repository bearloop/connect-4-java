import java.util.HashMap;

public final class Token {
  private static final char redToken = 'r';
  private static final char purpleToken = 'p';
  private static final char whiteToken = 'w';
  private static final char yellowToken = 'y';
  private static final char blueToken = 'b';
  private static final char greenToken = 'g';

  private static char[] humanTokens = { redToken, purpleToken };
  private static char[] computerTokens = { yellowToken, blueToken, greenToken, whiteToken };

  // Get a human token char
  public static char getHumanToken(int p) {
    return humanTokens[p];
  }

  // Get number of human tokens
  public static int getNumOfHumanTokens() {
    return humanTokens.length;
  }

  // Get a computer token char
  public static char getComputerToken(int p) {
    return computerTokens[p];
  }

  // Get number of computer tokens
  public static int getNumOfComputerTokens() {
    return computerTokens.length;
  }

  // Mapping of tokens to emojis
  public static final HashMap<String, String> tokensEmojisMapping() {

    HashMap<String, String> tokenToEmojis = new HashMap<>();

    tokenToEmojis.put(String.valueOf(redToken), "🔴");
    tokenToEmojis.put(String.valueOf(blueToken), "🔵");
    tokenToEmojis.put(String.valueOf(purpleToken), "🟣");
    tokenToEmojis.put(String.valueOf(greenToken), "🟢");
    tokenToEmojis.put(String.valueOf(yellowToken), "🟠");
    tokenToEmojis.put(String.valueOf(whiteToken), "⚪");

    return tokenToEmojis;
  }

}