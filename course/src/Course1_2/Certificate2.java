package Course1_2;

import java.util.List;

public class Certificate2 {
    String owner;
    List<Byte> publicKey;
    int keySize;
    String domain;

    public Certificate2(String owner, List<Byte> publicKey, int keySize, String domain) {
        this.owner = owner;
        //shallow copy issue bellow
        //this.publicKey = publicKey;
        this.publicKey=List.copyOf(publicKey);
        this.keySize = keySize;
        this.domain = domain;
    }

    @Override
    protected Certificate2 clone() throws CloneNotSupportedException {
        return new Certificate2(this.owner,this.publicKey,this.keySize,this.domain);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Certificate2)){
            return false;
        }
        return this.domain.equals(((Certificate2)obj).domain);
    }

    @Override
    public int hashCode() {
        //There is a method to get the hashCode for multiple values
        return this.domain.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n");
        sb.append("Certificate2{ ");
        sb.append("owner='").append(owner).append('\'');
        sb.append("publicKey: \n");
        for(byte value:this.publicKey){
            sb.append(String.format("%02x\n",value));
        }
        sb.append(", keySize=").append(keySize);
        sb.append(", domain='").append(domain).append('\'');
        sb.append(" }");
        return sb.toString();
    }
}
