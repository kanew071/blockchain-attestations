/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.4.2, Date: 20-Jul-2020.
 */
package dk.alexandra.stormbird.cheque.asnobjects;

import com.objsys.asn1j.runtime.*;

public class RedeemCheque extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_RedeemChequeRtkey._rtkey);
      Asn1Type._setLicLocation(_RedeemChequeRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "RedeemCheque";
   }

   public SignedCheque signedCheque;
   public MyAttestation attestation;
   public Proof proof;
   public Asn1BitString signatureValue;

   public RedeemCheque () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public RedeemCheque (
      SignedCheque signedCheque_,
      MyAttestation attestation_,
      Proof proof_,
      Asn1BitString signatureValue_
   ) {
      super();
      signedCheque = signedCheque_;
      attestation = attestation_;
      proof = proof_;
      signatureValue = signatureValue_;
   }

   public void init () {
      signedCheque = null;
      attestation = null;
      proof = null;
      signatureValue = null;
   }

   public int getElementCount() { return 4; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return signedCheque;
         case 1: return attestation;
         case 2: return proof;
         case 3: return signatureValue;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "signedCheque";
         case 1: return "attestation";
         case 2: return "proof";
         case 3: return "signatureValue";
         default: return null;
      }
   }


   public void decode
      (Asn1BerDecodeBuffer buffer, boolean explicit, int implicitLength)
      throws Asn1Exception, java.io.IOException
   {
      int llen = (explicit) ?
         matchTag (buffer, Asn1Tag.SEQUENCE) : implicitLength;

      init ();

      // decode SEQUENCE

      Asn1BerDecodeContext _context =
         new Asn1BerDecodeContext (buffer, llen);

      IntHolder elemLen = new IntHolder();

      // decode signedCheque

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.CONS, 16, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("signedCheque", -1);

         this.signedCheque = new SignedCheque();
         this.signedCheque.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("signedCheque", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "signedCheque");

      // decode attestation

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.CONS, 16, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("attestation", -1);

         this.attestation = new MyAttestation();
         this.attestation.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("attestation", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "attestation");

      // decode proof

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.CONS, 16, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("proof", -1);

         this.proof = new Proof();
         this.proof.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("proof", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "proof");

      // decode signatureValue

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.PRIM, 3, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("signatureValue", -1);

         this.signatureValue = new Asn1BitString();
         this.signatureValue.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("signatureValue", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "signatureValue");

      if (!_context.expired()) {
         Asn1Tag _tag = buffer.peekTag ();
         if (_tag.equals (Asn1Tag.UNIV, Asn1Tag.CONS, 16) ||
             _tag.equals (Asn1Tag.UNIV, Asn1Tag.PRIM, 3))  {
            throw new Asn1UnexpectedElementException();
         }

      }
   }

   public int encode (Asn1BerEncodeBuffer buffer, boolean explicit)
      throws Asn1Exception
   {
      int _aal = 0, len;

      // encode signatureValue

      if (this.signatureValue != null) {
         buffer.getContext().eventDispatcher.startElement("signatureValue", -1);

         len = this.signatureValue.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("signatureValue", -1);
      }
      else throw new Asn1MissingRequiredException ("signatureValue");

      // encode proof

      if (this.proof != null) {
         buffer.getContext().eventDispatcher.startElement("proof", -1);

         len = this.proof.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("proof", -1);
      }
      else throw new Asn1MissingRequiredException ("proof");

      // encode attestation

      if (this.attestation != null) {
         buffer.getContext().eventDispatcher.startElement("attestation", -1);

         len = this.attestation.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("attestation", -1);
      }
      else throw new Asn1MissingRequiredException ("attestation");

      // encode signedCheque

      if (this.signedCheque != null) {
         buffer.getContext().eventDispatcher.startElement("signedCheque", -1);

         len = this.signedCheque.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("signedCheque", -1);
      }
      else throw new Asn1MissingRequiredException ("signedCheque");

      if (explicit) {
         _aal += buffer.encodeTagAndLength (Asn1Tag.SEQUENCE, _aal);
      }

      return (_aal);
   }

   public void print (java.io.PrintWriter _out, String _varName, int _level)
   {
      indent (_out, _level);
      _out.println (_varName + " {");
      if (signedCheque != null) signedCheque.print (_out, "signedCheque", _level+1);
      if (attestation != null) attestation.print (_out, "attestation", _level+1);
      if (proof != null) proof.print (_out, "proof", _level+1);
      if (signatureValue != null) signatureValue.print (_out, "signatureValue", _level+1);
      indent (_out, _level);
      _out.println ("}");
   }

}