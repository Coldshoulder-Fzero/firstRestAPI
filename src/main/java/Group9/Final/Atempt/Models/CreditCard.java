package Group9.Final.Atempt.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class CreditCard{
    @Id
    @Column(name="full_name")
    private String full_name;
    @Column(name="card_number")
    private String card_number;
    @Column(name="SVC")
    private String SVC;
    @Column(name="exp")
    private String exp;
    @Column(name="zip_code")
    private String zip_code;

    public CreditCard(){
        
    }

    public CreditCard(String full_name, String card_number, String SVC, String exp, String zip_code){
        this.full_name = full_name;
        this.card_number = card_number;
        this.SVC = SVC;
        this.exp = exp;
        this.zip_code = zip_code;
    }

    public void setFullName(String full_name){
        this.full_name = full_name;
    }
    
    public String getFullName(){
        return this.full_name;
    }
    
    public void setCardNumber(String card_number){
        this.card_number = card_number;
    }

    public String getCardNumber(){
        return this.card_number;
    }

    public void setSVC(String SVC){
        this.SVC = SVC;
    }

    public String getSVC(){
        return this.SVC;
    }

    public void setExp(String exp){
        this.exp = exp;
    }
    
    public String getExp(){
        return exp;
    }

    public void setZipCode(String zip_code){
        this.zip_code = zip_code;
    }

    public String getZipCode(){
        return zip_code;
    }
    
}