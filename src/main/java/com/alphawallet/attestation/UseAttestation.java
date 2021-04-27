package com.alphawallet.attestation;

import com.alphawallet.attestation.IdentifierAttestation.AttestationType;
import com.alphawallet.attestation.core.ASNEncodable;
import com.alphawallet.attestation.core.AttestationCrypto;
import com.alphawallet.attestation.core.SignatureUtility;
import com.alphawallet.attestation.core.Validateable;
import com.alphawallet.attestation.core.Verifiable;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;

public class UseAttestation implements ASNEncodable, Verifiable, Validateable {
  private final SignedIdentityAttestation attestation;
  private final AttestationType type;
  private final FullProofOfExponent pok;
  private final AsymmetricKeyParameter sessionPublicKey;
  private final byte[] encoding;

  public UseAttestation(SignedIdentityAttestation attestation, AttestationType type, FullProofOfExponent pok, AsymmetricKeyParameter sessionPublicKey) {
    this.attestation = attestation;
    this.type = type;
    this.pok = pok;
    this.sessionPublicKey = sessionPublicKey;
    this.encoding = makeEncoding(attestation, type, pok, sessionPublicKey);
    constructorCheck();
  }

  public UseAttestation(byte[] derEncoding, AsymmetricKeyParameter attestationVerificationKey) {
    this.encoding = derEncoding;
    try {
      ASN1InputStream input = new ASN1InputStream(derEncoding);
      ASN1Sequence asn1 = ASN1Sequence.getInstance(input.readObject());
      int i = 0;
      this.attestation = new SignedIdentityAttestation(asn1.getObjectAt(i++).toASN1Primitive().getEncoded(), attestationVerificationKey);
      this.type = AttestationType.values()[
          ASN1Integer.getInstance(asn1.getObjectAt(i++)).getValue().intValueExact()];;
      this.pok = new FullProofOfExponent(asn1.getObjectAt(i++).toASN1Primitive().getEncoded());
      this.sessionPublicKey = SignatureUtility.restoreKeyFromSPKI(asn1.getObjectAt(i++).toASN1Primitive().getEncoded());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    constructorCheck();
  }

  private void constructorCheck() {
    if (!verify()) {
      throw new IllegalArgumentException("The use attestation object is not valid");
    }
  }

  private byte[] makeEncoding(SignedIdentityAttestation attestation, AttestationType type, FullProofOfExponent pok, AsymmetricKeyParameter sessionKey) {
    try {
      ASN1EncodableVector res = new ASN1EncodableVector();
      res.add(ASN1Sequence.getInstance(attestation.getDerEncoding()));
      res.add(new ASN1Integer(type.ordinal()));
      res.add(ASN1Sequence.getInstance(pok.getDerEncoding()));
      res.add(SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(sessionKey));
      return new DERSequence(res).getEncoded();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public SignedIdentityAttestation getAttestation() {
    return attestation;
  }

  public AttestationType getType() { return type; }

  public FullProofOfExponent getPok() {
    return pok;
  }

  public AsymmetricKeyParameter getSessionPublicKey() {
    return sessionPublicKey;
  }

  @Override
  public byte[] getDerEncoding() {
    return encoding;
  }

  @Override
  public boolean verify() {
    return attestation.verify() && AttestationCrypto.verifyFullProof(pok);
  }

  @Override
  public boolean checkValidity() {
    return attestation.checkValidity();
  }
}
