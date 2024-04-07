package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    // auto setup repositories on startup
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    //    will run when Spring executes
    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Publisher aw = new Publisher();
        aw.setPublisherName("Addison Wesley");
        aw.setAddress("907 Rodeo Drive");
        aw.setCity("Beverly Hills");
        aw.setState("CA");
        aw.setZip("90210");


        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);
        Publisher awSaved = publisherRepository.save(aw);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Publisher wx = new Publisher();
        wx.setPublisherName("Wrox Media");
        wx.setAddress("908 Rodeo Drive");
        wx.setCity("Beverly Hills");
        wx.setState("CA");
        wx.setZip("90210");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher wxSaved = publisherRepository.save(wx);

        ericSaved.getBooks().add(dddSaved);
        awSaved.getBooks().add(dddSaved);
        dddSaved.getAuthors().add(ericSaved);
        dddSaved.setPublisher(awSaved);

        rodSaved.getBooks().add(noEJBSaved);
        wxSaved.getBooks().add(noEJBSaved);
        noEJBSaved.getAuthors().add(rodSaved);
        noEJBSaved.setPublisher(wxSaved);


// persist book association
        // going forward include this step in testing
//        to very persistence.
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);
        publisherRepository.save(wxSaved);
        publisherRepository.save(awSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count:" +authorRepository.count());
        System.out.println("Book Count:"+bookRepository.count());
        System.out.println("Publisher Count:"+publisherRepository.count());
//        System.out.println(dddSaved);
//        System.out.println(wxSaved);

    }
}
