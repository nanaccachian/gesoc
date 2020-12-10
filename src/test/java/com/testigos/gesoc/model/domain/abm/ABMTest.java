package com.testigos.gesoc.model.domain.abm;

import com.testigos.gesoc.GesocApplication;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GesocApplication.class)
public class ABMTest {

    @Autowired
    private RegistroRepository repo;

    @Test
    public void loadingData() {
        Registro a1 = new Registro(TipoRegistro.ALTA, null, "Se modifico el campo a1");
        Registro a2 = new Registro(TipoRegistro.MODIFICACION, null, "Se modifico el campo a2");

        //save product, verify has ID value after save
        Assert.assertNull(a1.getId());
        Assert.assertNull(a2.getId());//null before save
        this.repo.save(a1);
        this.repo.save(a2);

    }
    @Test
    public void update(){
        /*Test update*/
        Registro userB = repo.findByDescripcion("Se modifico el campo a1");
        userB.setDescripcion("He sido modificado");
        repo.save(userB);
        Registro userC= repo.findByDescripcion("He sido modificado");
        Assert.assertNotNull(userC);
        Assert.assertEquals("He sido modificado", "He sido modificado");
    }

    @Test
    public void findByTipoRegistro(){
        /*Test update*/
        List<Registro> reg = repo.findAllByTipoRegistro(TipoRegistro.ALTA);
        Assert.assertNotNull(reg);
        Assert.assertEquals(1, reg.size());
    }

    @Test
    public void tearDown() {
        this.repo.deleteAll();
    }
}
