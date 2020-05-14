package com.nsi.lab5.commandwork;

/**
 * Worker's Organization
 */
public class Organization {
    /**
     * Name of organization
     */
    private String fullName; //Поле не может быть null
    /**
     * {@link OrganizationType}
     */
    private OrganizationType type; //Поле не может быть null
    /**
     * Address of organization
     */
    private Address officialaddress; //Поле не может быть null
    public Organization (String in){
        String str[] = in.split(",",3);
        fullName = str[0];
        type = OrganizationType.getOrgType(str[1]);
        officialaddress = new Address(str[2].trim());

    }

    /**
     *
     * @return String in usual format
     */
    @Override
    public String toString() {
        return (fullName+ "|" + this.officialaddress + /*"|This i test"*/  "|" + this.type);
    }
    /**
     *
     * @return String in csv format
     */
    public String tocsv() {
        return (fullName+ "," + this.type + /*"|This i test"*/  "," + this.officialaddress);
    }
}
