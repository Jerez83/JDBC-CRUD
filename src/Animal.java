public class Animal {

    private int id;
    private String alias;
    private char sexo;
    private int peso;          // >=0

    public Animal() {
    }

    public Animal(String alias, char sexo, int peso) {
        this.alias = alias;
        this.sexo = sexo;
        this.peso = peso;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public char getSexo() {
        return sexo;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", sexo=" + sexo +
                ", peso=" + peso +
                '}';
    }
}
