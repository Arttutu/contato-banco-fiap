package br.com.fiap.contatos.dao;

import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContatoDao {

    private final EntityManager  em;

    public ContatoDao(EntityManager em){
        this.em = em;
    }
    public  void salvar (Contato contato){
        em.persist(contato);
    }
    public  void atualizar (Contato contato){
        em.merge(contato);
   }
    public  void excluir (@NotNull Contato contato){
        Contato contatoExcluir = em.find(Contato.class, contato.getId());
        em.remove(contatoExcluir);
    }
    public void consultarId(Long id){
        Contato contatoConsulta = em.find(Contato.class, id);
        if (contatoConsulta == null){
            System.out.println("contato nao encontrado");
        }else{
            System.out.println("-------------------------");
            System.out.println(contatoConsulta);
            System.out.println("------------------------");
        }

    }
    public List<Contato> consultarTodos (){
        //  SQL    ---> SELECT * FROM tbl_contatos ORDER BY nome ASC

        //JPQL
        String consulta  = "SELECT c FROM Contato c ORDER BY nome ASC";
        return  em.createQuery(consulta).getResultList();
    }
    public List<Contato> consultarEmail ( String email) {
        //JPQL
        String consulta = "SELECT c FROM Contato c WHERE email = :email_procurado";
        return  em.createQuery(consulta, Contato.class)
                .setParameter("email_procurado", email)
                .getResultList();

    }
}
