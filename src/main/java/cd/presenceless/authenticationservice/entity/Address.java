package cd.presenceless.authenticationservice.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private Long cid;
    private String province;
    private String ville;
    private String commune;
    private String quartier;
    private String avenue;
    private Integer no;

    public Address(Long cid, String province, String ville, String commune, String quartier, String avenue, Integer no) {
        this.cid = cid;
        this.province = province;
        this.ville = ville;
        this.commune = commune;
        this.quartier = quartier;
        this.avenue = avenue;
        this.no = no;
    }

}
