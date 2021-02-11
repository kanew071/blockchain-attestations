package org.tokenscript.auth;

import com.alphawallet.token.web.service.CryptoFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Class for EIP712 JSON issuance and validation for authentication
 */
public abstract class Eip712Common {
  protected static final String JSON_RPC_VER = "2.0";
  protected final CryptoFunctions cryptoFunctions;
  protected final ObjectMapper mapper;

  public Eip712Common() {
    Security.addProvider(new BouncyCastleProvider());
    this.cryptoFunctions = new CryptoFunctions();
    this.mapper = new ObjectMapper();
  }

  protected boolean isValidDomain(String domain) {
    try {
      // Check if we get a malformed exception
      new URL(domain);
    } catch (MalformedURLException e) {
      return false;
    }
    return true;
  }

}
