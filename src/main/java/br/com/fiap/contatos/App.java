package br.com.fiap.contatos;
import br.com.fiap.contatos.dao.Conexao;
import br.com.fiap.contatos.dao.ContatoDao;
import br.com.fiap.contatos.dao.TipoContatoDao;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.model.TipoContato;
import jakarta.persistence.EntityManager;


import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {
        EntityManager em = Conexao.getEntityManager();
        consultarTipoId(em, 1L);



    }
    public static void cadastrar( EntityManager em){

        TipoContato tipoContato = new TipoContato();
        tipoContato.setTipo("Família");

        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);

        em.getTransaction().begin();
        tipoContatoDao.salvar(tipoContato);

        Contato contato = new Contato();
        contato.setNome("Camilia");
        contato.setEmail("camila@gmail.com");
        contato.setDataNascimento(LocalDate.of(2008, 9, 2));
        contato.setTipoContato(tipoContato);


        ContatoDao contatoDao = new ContatoDao(em);
        contatoDao.salvar(contato);
        em.getTransaction().commit();
    }
    public static void atualizar( EntityManager em ){
        Contato contato = new Contato();
        contato.setId(4L);
        contato.setNome("Ana Clara");
        contato.setEmail("sana59986@fiap.com");
        contato.setDataNascimento(LocalDate.of(1998, 9, 21));
        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();
        contatoDao.atualizar(contato);
        em.getTransaction().commit();

    }
    public static void excluir(EntityManager em, long id){
        Contato contato = new Contato();
        contato.setId(id);
        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();
        contatoDao.excluir(contato);
        em.getTransaction().commit();

    }
    public static void consultarId (EntityManager em, long id){
        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();
        contatoDao.consultarId(id);
        em.getTransaction().commit();

    }
    public static void consultarTipoId (EntityManager em, long id){
        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);
        TipoContato tipoContatoBuscado = new TipoContato();
        tipoContatoBuscado.setId(id);
        TipoContato tipoContadoEncontrado  = new TipoContato();
        tipoContadoEncontrado = tipoContatoDao.consultarTipoID(tipoContatoBuscado);
        System.out.println(tipoContadoEncontrado.getTipo());

    }
    public static void consultarTodos (EntityManager em){
        ContatoDao contatoDao = new ContatoDao(em);
        List<Contato> contatos = contatoDao.consultarTodos();
        for( Contato contato : contatos){
            System.out.println("----------------------");
            System.out.println(contato.toString());
        }
        System.out.println("Fim dos registros...");

    }
    public static void consultarEmail (EntityManager em, String email){
        ContatoDao contatoDao = new ContatoDao(em);
        List<Contato> contatos = contatoDao.consultarEmail(email);
        for( Contato contato : contatos){
            System.out.println("----------------------");
            System.out.println(contato.toString());
        }
        System.out.println("Fim dos registros...");

    }
}

