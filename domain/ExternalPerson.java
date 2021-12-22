package domain;

public class ExternalPerson {
    
    private int externalPersonId;
    private String name;
    private String email;
    private String organisation;
    private String role;

    public ExternalPerson(int externalPersonId, String name, String email, String organisation, String role) {
        this.externalPersonId = externalPersonId;
        this.name = name;
        this.email = email;
        this.organisation = organisation;
        this.role = role;
    }

    public ExternalPerson(String name, String email, String organisation, String role) {
        this.name = name;
        this.email = email;
        this.organisation = organisation;
        this.role = role;
    }

    public int getExternalPersonId() {
        return externalPersonId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getRole() {
        return role;
    }

}
