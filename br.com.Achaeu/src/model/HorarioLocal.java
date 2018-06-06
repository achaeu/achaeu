/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;

/**
 *
 * @author igor-bueno
 */
public class HorarioLocal extends IEntidade{
    private Time entrada1;
    private Time saida1;
    private Time entrada2;
    private Time saida2;
    private Integer funcionamento;
    private Integer diaSemana;

    public HorarioLocal() {
    }

    public HorarioLocal(Integer id, Time entrada1, Time saida1, Time entrada2, Time saida2, Integer funcionamento, Integer diaSemana) {
        super(id);
        this.entrada1 = entrada1;
        this.saida1 = saida1;
        this.entrada2 = entrada2;
        this.saida2 = saida2;
        this.funcionamento = funcionamento;
        this.diaSemana = diaSemana;
    }

    public Time getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(Time entrada1) {
        this.entrada1 = entrada1;
    }

    public Time getSaida1() {
        return saida1;
    }

    public void setSaida1(Time saida1) {
        this.saida1 = saida1;
    }

    public Time getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(Time entrada2) {
        this.entrada2 = entrada2;
    }

    public Time getSaida2() {
        return saida2;
    }

    public void setSaida2(Time saida2) {
        this.saida2 = saida2;
    }

    public Integer getFuncionamento() {
        return funcionamento;
    }

    public void setFuncionamento(Integer funcionamento) {
        this.funcionamento = funcionamento;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    public String toString() {
        return "HorarioLocal{" + "entrada1=" + entrada1 + ", saida1=" + saida1 + ", entrada2=" + entrada2 + ", saida2=" + saida2 + ", funcionamento=" + funcionamento + ", diaSemana=" + diaSemana + '}';
    }
}
