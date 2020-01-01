package de.phib.tasket.data.collection;

import de.phib.tasket.data.shared.error.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionControllerTest {

    @Autowired
    private CollectionController collectionController;

    @Test
    public void getAllCollections_verifyNotEmpty() {
        assertThat(this.collectionController.getAllCollections()).isNotEmpty();
    }

    @Test
    public void createCollection_verifyTitle() {
        Collection newCollection = new Collection("Test Collection Title");
        Collection createdCollection = this.collectionController.createCollection(newCollection);

        assertThat(newCollection.getTitle()).isEqualTo(createdCollection.getTitle());
    }

    @Test
    public void getCollection_verifyTitle() {
        assertThat(this.collectionController.getCollection("1").getTitle()).isEqualTo("Mo. 15.04.2019");
    }

    @Test
    public void updateCollection_verifyTitle() {
        Collection newCollection = new Collection("Test Collection Title");
        Collection updatedCollection = this.collectionController.updateCollection("1", newCollection);

        assertThat(newCollection.getTitle()).isEqualTo(updatedCollection.getTitle());
    }


    @Test
    public void deleteCollection_verifyException() {
        this.collectionController.deleteCollection("2");

        assertThatThrownBy(() -> {
            this.collectionController.getCollection("2");
        }).isInstanceOf(ObjectNotFoundException.class);
    }


    @Test
    @Transactional
    public void getEvents() {
        assertThat(this.collectionController.getEvents("1")).size().isEqualTo(3);
    }

}
