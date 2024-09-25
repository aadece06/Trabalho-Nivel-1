package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    
    public void inserir(PessoaJuridica pessoa) {
        pessoasJuridicas.add(pessoa);
    }

    
    public ArrayList<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

   
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pessoasJuridicas);
        }
    }

   
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            pessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }

    
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pj : pessoasJuridicas) {
            if (pj.getId() == id) {
                return pj;
            }
        }
        return null;
    }

   
    public void alterar(int id, PessoaJuridica novaPessoaJuridica) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            if (pessoasJuridicas.get(i).getId() == id) {
                pessoasJuridicas.set(i, novaPessoaJuridica); 
                return;
            }
        }
    }

   
    public void excluir(int id) {
        pessoasJuridicas.removeIf(pj -> pj.getId() == id);
    }

    
    public void exibirTodas() {
        if (pessoasJuridicas.isEmpty()) {
            System.out.println("Nenhuma pessoa jurídica cadastrada.");
        } else {
            for (PessoaJuridica pj : pessoasJuridicas) {
                pj.exibir();
            }
        }
    }

    public void alterar(PessoaJuridica pessoa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
