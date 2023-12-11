package dieguito.medic;

public class Medic {
    private String nome;
    private double salario;
    private int idx_especilizacao;
    private String[] especilizacao = {"Cardiologista","Urulogista","Enfermeiro"};

    public Medic(){}
    public Medic(String nome,int idx_especilizacao){
        this.nome = nome;
        this.idx_especilizacao = idx_especilizacao;

    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public double getSalario(){
        return this.salario;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }
    public String[] getEspecializacao(){
        return this.especilizacao;
    }
    public void setEspecilizacao(String[] especilizacao){
        this.especilizacao = especilizacao;
    }
    public int getIdx_especilizacao(){
        return this.idx_especilizacao;
    }
    public void setIdx_especilizacao(int idx_especilizacao){
        this.idx_especilizacao = idx_especilizacao;
    }
    @Override
    public String toString() {
        return "Dr."+nome+", especialista em "+especilizacao[this.idx_especilizacao]+" tem como seu salário base : "+salario;
    }
    @Override
    public boolean equals(Object  medic) {
        return this.nome.equalsIgnoreCase(((Medic)medic).nome);
    }
}
