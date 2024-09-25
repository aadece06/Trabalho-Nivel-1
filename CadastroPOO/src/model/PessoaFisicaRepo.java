package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<>();

 
    public void inserir(PessoaFisica pessoa) {
        pessoasFisicas.add(pessoa);
    }

    
    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pessoasFisicas);
        }
    }

   
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            pessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }

   
    public PessoaFisica obter(int id) {
        for (PessoaFisica pf : pessoasFisicas) {
            if (pf.getId() == id) {
                return pf;
            }
        }
        return null;
    }

    
    public void alterar(int id, PessoaFisica novaPessoaFisica) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            if (pessoasFisicas.get(i).getId() == id) {
                pessoasFisicas.set(i, novaPessoaFisica);
                return;
            }
        }
    }

   
    public void excluir(int id) {
        pessoasFisicas.removeIf(pf -> pf.getId() == id); 
    }

    
    public void exibirTodas() {
        if (pessoasFisicas.isEmpty()) {
            System.out.println("Nenhuma pessoa física cadastrada.");
        } else {
            for (PessoaFisica pf : pessoasFisicas) {
                pf.exibir();
            }
        }
    }

    public void alterar(PessoaFisica pessoa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

