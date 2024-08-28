
package ru;

class Cardapio 
{
    private String diaSem, carboidrato, proteina, salada, bebida, sobremesa, fruta;

    public Cardapio(String diaSem, String carboidrato, String proteina, String salada, String bebida, String sobremesa, String fruta, String sobremesaSelecionada, String frutaSelecionada) {
        this.diaSem = diaSem;
        this.carboidrato = carboidrato;
        this.proteina = proteina;
        this.salada = salada;
        this.bebida = bebida;
        this.sobremesa = sobremesa;
        this.fruta = fruta;
    }

    public String getDiaSem() {
        return diaSem;
    }

    public void setDiaSem(String diaSem) {
        this.diaSem = diaSem;
    }

    public String getCarboidrato() {
        return carboidrato;
    }

    public void setCarboidrato(String carboidrato) {
        this.carboidrato = carboidrato;
    }

    public String getProteina() {
        return proteina;
    }

    public void setProteina(String proteina) {
        this.proteina = proteina;
    }

    public String getSalada() {
        return salada;
    }

    public void setSalada(String salada) {
        this.salada = salada;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }

    public String getFruta() {
        return fruta;
    }

    public void setFruta(String fruta) {
        this.fruta = fruta;
    }

    @Override
    public String toString() {
        return "CARDAPIO\n" + "\nDia: " + diaSem + "\nCarboidrato: " + carboidrato + "\nProteina: " + proteina + "\nSalada: " + salada + "\nBebida: " + bebida + "\nSobremesa: " + sobremesa + "\nFruta: " + fruta;
    }
    
}
