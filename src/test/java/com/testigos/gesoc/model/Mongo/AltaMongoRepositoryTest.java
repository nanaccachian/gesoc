package com.testigos.gesoc.model.Mongo;

import com.testigos.gesoc.GesocApplication;
import com.testigos.gesoc.model.ABM.Alta;
import com.testigos.gesoc.persistence.MongoRepositories.AltaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GesocApplication.class)
public class AltaMongoRepositoryTest {

    @Autowired
    private AltaRepository repo;

    @Test
    public void loadingData() {
        Alta a1 = new Alta("Se modifico el campo a1");
        Alta a2 = new Alta("Se modifico el campo a2");

        //save product, verify has ID value after save
        Assert.assertNull(a1.getId());
        Assert.assertNull(a2.getId());//null before save
        this.repo.save(a1);
        this.repo.save(a2);

    }
    @Test
    public void update(){
        /*Test update*/
        Alta userB = repo.findByDescripcion("Se modifico el campo a1");
        userB.setDescripcion("He sido modificado");
        repo.save(userB);
        Alta userC= repo.findByDescripcion("He sido modificado");
        Assert.assertNotNull(userC);
        Assert.assertEquals("He sido modificado", "He sido modificado");
    }

}
