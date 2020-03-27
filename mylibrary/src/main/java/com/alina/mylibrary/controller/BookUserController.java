package com.alina.mylibrary.controller;


import com.alina.mylibrary.model.*;
import com.alina.mylibrary.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class BookUserController {

    @Autowired
    private BookUserRepository bookUserRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private QuizzRepository quizzRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BooksAuthorsRepository booksAuthorsRepository;
    private VoucherUserRepository voucherUserRepository;
    private CategoryRepository categoryRepository;
    private BooksCategoriesRepository booksCategoriesRepository;
    private ComplaintRepository complaintRepository;
    private ReviewRepository reviewRepository;


    public BookUserController(BookUserRepository bookUserRepository,
                              PublisherRepository publisherRepository,
                              QuizzRepository quizzRepository,
                              VoucherRepository voucherRepository,
                              BookRepository bookRepository,
                              AuthorRepository authorRepository,
                              BooksAuthorsRepository booksAuthorsRepository,
                           VoucherUserRepository voucherUserRepository,
                              CategoryRepository categoryRepository,
                              BooksCategoriesRepository booksCategoriesRepository,
                              ComplaintRepository complaintRepository,
                              ReviewRepository reviewRepository
    ) {
        this.bookUserRepository = bookUserRepository;
        this.publisherRepository = publisherRepository;
        this.quizzRepository = quizzRepository;
        this.voucherRepository = voucherRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.booksAuthorsRepository = booksAuthorsRepository;

        this.voucherUserRepository = voucherUserRepository;
        this.categoryRepository = categoryRepository;
        this.booksCategoriesRepository = booksCategoriesRepository;
        this.complaintRepository = complaintRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/all")
    public Iterable<BookUser> all(){


        //TODO o carte are mai multe review uri,cum se face acest lucru
        Review testReview=new Review();
        testReview.setReviewerName("foarte buna");
        testReview.setTextReview("Am citit-o pe nerasuflate");
        reviewRepository.save(testReview);

        Complaint comTest=new Complaint();
        comTest.setSubject("Cerere");
        comTest.setText("Ati putea aduce carti scrise de Cecelia Ahern?");
   //     complaintRepository.save(comTest);
        //   ....................................................................................category............................................................................


        Category c1=new Category();
        c1.setCategoryDescription("Științifico-fantasticul (numit și știință-ficțiune,[1] SF sau science-fiction, pronunțat [sa-iăns fic-șăn] sau [sa-ins fic-șăn], din engl. science fiction /ˈsaɪ.əns ˈfɪk.ʃən/) este un gen artistic prezent cu precădere în literatură și cinematografie, a cărui temă principală este impactul științei și tehnologiei asupra societății și persoanelor.");
        c1.setCategoryTitle("SF");
    //    categoryRepository.save(c1);

     //   ....................................................................................publisher............................................................................
    //    List<Publisher> pub=publisherRepository.findAll();
        Publisher p1=new Publisher();
        p1.setPublisherTitle("Corint");
      //  publisherRepository.save(p1);
        //   ...................................................................................authors........................................................................................


        List<Author> authors=authorRepository.findAll();
        Author a1=new Author();
        a1.setFirstName("Paulo");
        a1.setLastName("Coelho");
        try {
         //   authorRepository.save(a1);
        }catch(IllegalArgumentException e)
        {
            System.out.println("*****first_name:"+ a1.getFirstName()+ "***lastname:"+ a1.getLastName()+ "***id:"+ a1.getAuthorId());
        }


        Author a2=new Author();
        a2.setFirstName("Sandra");
        a2.setLastName("Brown");
        try {
          //  authorRepository.save(a2);
        }catch(IllegalArgumentException e)
        {
            System.out.println("*****first_name:"+ a2.getFirstName()+ "***lastname:"+ a2.getLastName()+ "***id:"+ a2.getAuthorId());
        }


        //   ...................................................................................BOOKS........................................................................................

       List<Book> books=bookRepository.findAll();



       Book book2=new Book();
        book2.setBookDescription("O carte captivanta, in care este povestit pelerinajul lui Paulo Coelho catre Santiago de Compostela, pe un drum medieval ce incepe in Pirinei si strabate nordul Spaniei. Pelerinajul facut de autor in 1986 a inspirat acest roman de aventuri care este totodata o fascinanta parabola despre nevoia de a gasi propria cale in viata si despre descoperirea faptului ca miracolul se ascunde intotdeauna in pasii oamenilor obisnuiti.\n" +
                "\n" +
                "Jurnalul unui Mag ocupa un loc important in opera lui Paulo Coelho nu doar pentru ca este prima dintre cartile lui importante, publicata inainte de Alchimistul, ci si pentru ca este expresia completa a umanismului filozofiei lui Paulo Coelho si a profunzimii cautarilor sale.\n" +
                "\n" +
                "Am ajuns ieri in oras, dupa ce am luat autobuzul care face curse regulate intre Pedrafita – aproape de Cebreiro – si Compostela. In patru ore am parcurs cei o suta cincizeci de kilometri care desparteau cele doua orase si mi-am amintit de calatoria cu Petrus – uneori aveam nevoie de doua saptamani ca sa strabatem o distanta ca asta. Peste putin aveam sa merg la mormantul Sfantului Iacob sa las acolo icoana Sfintei Fecioare Intrupate, montata in scoici. Apoi, cat mai curand posibil, voi lua avionul inapoi spre Brazilia, fiindca am multa treaba. [...] Ma gandesc sa scriu o carte despre tot ce mi s-a intamplat. Dar este inca un gand departat... - Paulo Coelho");
        book2.setBookDimension("6x16x10");
        book2.setBookLanguage("Romana");
        book2.setBookPrice(89);
        book2.setBookRating(4);
      //  TODO de schimbat titlul in spioana
        book2.setBookTitle("Spioana");
        book2.setBookWeight(0.5f);
        book2.setBookYear(1998);
        book2.setNumberOfBoooks(15);
        book2.setCoverType("hardcover");
        book2.setNumberOfPages(543);
        book2.setNumberOfReviews(0);
        book2.setPublisher(p1);
        book2.setNumberofVolumes(1);
//
//    bookRepository.save(book2);


      Book book3=new Book();
        book3.setBookDescription("Pe buna dreptate, contemporanii l-au numi regele forului, iar in vremurile urmatoare a castigat atata glorie, incat Cicero nu este numele unui om, ci al elocventei. - Quintilian\n" +
                "\n" +
                "Cat despre elocventa lui Cicero, e mai presus de orice comparatie. Cred ca niciodata vreun om nu-l va egala. - Montaigne\n" +
                "\n" +
                "Cicero are constiinta valorii sale si a importantei oratoriei in statul roman. El face un vibrant elogiu oratoriei in relatile cu problemele majore ale cetatii. Forta oratoriei, intr-o societate libera, este mirifica. Oratorul cucereste forul, senatul, tribunele, salveaza libertatea sau viata cetatenilor si contribuie la propasirea statului. - Traian Diaconescu");
        book3.setBookDimension("10x0.9x13");
        book3.setBookLanguage("Romana");
        book3.setBookPrice(89);
        book3.setBookRating(4);
        book3.setBookTitle("Arta oratoriei ");
        book3.setBookWeight(0.5f);
        book3.setBookYear(1998);
        book3.setNumberOfBoooks(15);
        book3.setCoverType("hardcover");
        book3.setNumberOfPages(543);
        book3.setNumberOfReviews(0);
        book3.setNumberofVolumes(1);
        book3.setPublisher(p1);

     //   bookRepository.save(book3);



        Book book1=new Book();
        book1.setBookDescription("Romanul Unsprezece minute este povestea Mariei, o fata dintr-un sat brazilian, ale carei prime intalniri inocente cu dragostea o lasa cu inima franta. La o varsta frageda, ea se convinge ca nu va gasi niciodata iubirea adevarata, crezand in schimb ca „dragostea este un lucru cumplit, care te face sa suferi...“. O intalnire intamplatoare in Rio de Janeiro o determina sa plece la Geneva, unde viseaza sa gaseasca faima si bogatie, dar sfarseste in strada, ca prostituata.\n" +
                "\n" +
                "In Geneva, Maria se indeparteaza tot mai mult de iubire, devenind tot mai fascinata de sex. In cele din urma, conceptia ei lipsita de speranta despre dragoste este pusa la incercare cand cunoaste un tanar si chipes pictor. In aceasta odisee a descoperirii de sine, Maria are de ales intre a urma o cale\n" +
                "a intunericului – placerea sexuala de dragul placerii sexuale – sau a risca totul pentru a gasi „lumina launtrica“ si potentialitatile sexului sacru, ale sexului in contextul dragostei.\n" +
                "\n" +
                "In acest palpitant si indraznet roman, Paulo Coelho analizeaza cu sensibilitate natura spirituala a sexului si a iubirii si ne invita sa ne confruntam cu propriile prejudecati, cu propriii demoni, sa ne aflam propria „lumina launtrica“.");
        book1.setBookDimension("777");
        book1.setBookLanguage("Romana");
        book1.setBookPrice(30);
        book1.setBookRating(4);
        book1.setBookTitle("11 minute");
        book1.setBookWeight(0.5f);
        book1.setBookYear(1978);
        book1.setNumberOfBoooks(122);
        book1.setCoverType("hardcover");
        book1.setNumberOfPages(543);
        book1.setNumberOfReviews(0);
        book1.setNumberofVolumes(1);
        book1.setPublisher(p1);

//
        List<Book> bks=bookRepository.findAll();
        book1=bks.get(1);
        book2=bks.get(2);
        book3=bks.get(3);
        BooksCategories bcLink=new BooksCategories();
        bcLink.setBooksC(book1);
        bcLink.setCategories(c1);
      //  booksCategoriesRepository.save(bcLink);

        BooksCategories bcLink2=new BooksCategories();
        bcLink2.setBooksC(book2);
        bcLink2.setCategories(c1);
    //    booksCategoriesRepository.save(bcLink2);


        BooksCategories bcLink3=new BooksCategories();
        bcLink3.setBooksC(book3);
        bcLink3.setCategories(c1);
//        booksCategoriesRepository.save(bcLink3);



        //  bookRepository.save(book1);

        //   ..................................................................................books+authors........................................................................................


      List<BooksAuthors> booksAuthorsLink=booksAuthorsRepository.findAll();
        BooksAuthors lba1=new BooksAuthors();
        lba1.setAuthorId(a1);
        lba1.setBookId(book1);

    //    booksAuthorsRepository.save(lba1);


        BooksAuthors lba2=new BooksAuthors();
        lba2.setAuthorId(a2);
        lba2.setBookId(book2);
      //  booksAuthorsRepository.save(lba2);


         BooksAuthors lba3=new BooksAuthors();
        lba3.setAuthorId(a1);
        lba3.setBookId(book3);
      //  booksAuthorsRepository.save(lba3);
        //   ...................................................................................users........................................................................................

//        //todo de schimbat length pt mail
//        List<BookUser> users = bookUserRepository.findAll();
        BookUser admin=new BookUser();
        admin.setAppartmentNumber(35);
        admin.setBlock("17");
        admin.setCity("Bucuresti");
        admin.setEmailAdress("olaru@gmail.com");
        admin.setFirstName("Alina");
        admin.setLastName("Olaru");
        admin.setFloor(8);
        admin.setUsername("alinaaolaru");
        admin.setPassword("5555");
        admin.setPhoneNumber("0726080518");
        admin.setProvince("Bucuresti");
        admin.setStreetName("Berceni");
        admin.setUserPrivilege(true);
        admin.setNewsletter(true);
        admin.setAdminPrivilege(true);
        admin.setStreetNumber(17);
        admin.setFloor(8);
        admin.setAppartmentNumber(65);
        admin.setCountry("Romania");
     //bookUserRepository.save(admin);
//
        BookUser firstUser=new BookUser();
        firstUser.setAppartmentNumber(35);
        firstUser.setBlock("17");
        firstUser.setCity("Bucuresti");
        firstUser.setEmailAdress("testt@gmail.com");
        firstUser.setFirstName("test");
        firstUser.setLastName("test");
        firstUser.setFloor(8);
        firstUser.setUsername("test");
        firstUser.setPassword("5555");
        firstUser.setPhoneNumber("0726080518");
        firstUser.setProvince("Bucuresti");
        firstUser.setStreetName("Berceni");
        firstUser.setUserPrivilege(true);
        firstUser.setNewsletter(true);
        firstUser.setAdminPrivilege(false);
        firstUser.setStreetNumber(17);
        firstUser.setFloor(8);
        firstUser.setAppartmentNumber(65);
        firstUser.setCountry("Romania");
     //   bookUserRepository.save(firstUser);


        //TODO UNICAT PE EMAIL CA S A PUS DE 2 ORI
        //TODO DE STERS DUPLICATELE DE AICI SI DE LA QUIZZEZ

    //   ...................................................................................quizzez........................................................................................
//        List<Quizz> q = quizzRepository.findAll();
//
        Date aa=new Date(2020,3,1);
        Date bbbb=new Date(2020,4,15);
        Quizz newQuizz = new Quizz();
        newQuizz.setQuizzQuestion("Citatul “Just as I succeeded in finding all the answers,\n" +
                " all the questions changed.“  este al lui: ");
        newQuizz.setNumberOfQuestions(3);
        newQuizz.setQuizzAnswers("Paulo Coelho.Mihai Eminescu.Sandra Brown");
        newQuizz.setQuizzCorrectAnswer("Paulo Coelho");
        newQuizz.setQuizzStartDate(aa);
        newQuizz.setQuizzEndDate(bbbb);
        System.out.println(newQuizz);
    //    quizzRepository.save(newQuizz);



        Date aaa=new Date(2020,4,16);
        Date bb=new Date(2020,8,15);
        Quizz newQuizzz = new Quizz();
        newQuizzz.setQuizzQuestion("Opera De legibus (Marcus Tullius Cicero este:");
        newQuizzz.setNumberOfQuestions(4);
        newQuizzz.setQuizzAnswers("Dialog politic.Scriere pe tema existentei.Scriere pe tema divinitatii.Expunere a problemei destinului.");
        newQuizzz.setQuizzCorrectAnswer("Dialog politic.");
        newQuizzz.setQuizzStartDate(aaa);
        newQuizzz.setQuizzEndDate(bb);
        System.out.println(newQuizzz);
     //   quizzRepository.save(newQuizzz);
//
//    //   ...................................................................................vouchers........................................................................................
        Voucher Va = new Voucher();
        Va.setVoucherTitle("Voucher 10%");
        Va.setVoucherDescription("5% ka orice comanda");
        Va.setVoucherMaximumUses(10);
        Va.setVoucherPrice(10.0f);
        Va.setVoucherStartDate(new Date(2020,1,1));
        Va.setVoucherEndDate(new Date(2020,12,31));
        Va.setQuizzez(newQuizz);

        Voucher Vb = new Voucher();
        Vb.setVoucherTitle("Voucher 25%");
        Vb.setVoucherDescription("25& la orice comanda");
        Vb.setVoucherMaximumUses(1);
        Vb.setVoucherPrice(25.5f);
        Vb.setVoucherStartDate(new Date(2020,4,1));
        Vb.setVoucherEndDate(new Date(2020,12,31));
        Vb.setQuizzez(newQuizzz);
//
//        this.voucherRepository.save(a);
//        this.voucherRepository.save(b);

        //   ...................................................................................uservoucher........................................................................................

        VoucherUser first_voucher=new VoucherUser();
        first_voucher.setUsed(false);
        first_voucher.setVouchers(Va);
        first_voucher.setUsersWithVouchers(firstUser);
   //    this.voucherUserRepository.save(first_voucher);

        VoucherUser sec_voucher=new VoucherUser();
        sec_voucher.setUsed(false);
        sec_voucher.setVouchers(Va);
        sec_voucher.setUsersWithVouchers(firstUser);
   //     this.voucherUserRepository.save(sec_voucher);

//        Quizz newQuizz = new Quizz();
//        newQuizz.setQuizzQuestion("asdsa");
//        newQuizz.setNumberOfQuestions(2);
//        newQuizz.setQuizzAnswers("vfefv");
//        newQuizz.setQuizzCorrectAnswer("szcdcvdffcfv");
//
//        quizzRepository.save(newQuizz);
//
//        Voucher a = new Voucher();
//        a.setVoucherTitle("a");
//        a.setVoucherDescription("b");
//        a.setVoucherMaximumUses(5);
//        a.setVoucherPrice(5.5f);
//        a.setVoucherStartDate(new Date());
//        a.setVoucherEndDate(new Date());
//        a.setQuizzez(newQuizz);
//
//        Voucher b = new Voucher();
//        b.setVoucherTitle("q");
//        b.setVoucherDescription("z");
//        b.setVoucherMaximumUses(5);
//        b.setVoucherPrice(5.5f);
//        b.setVoucherStartDate(new Date());
//        b.setVoucherEndDate(new Date());
//        b.setQuizzez(newQuizz);
//
//        this.voucherRepository.save(a);
//        this.voucherRepository.save(b);






//        this.bookUserRepository.deleteAll();
//        BookUser b1=new BookUser(
//                "Alina",
//                "Olaru",
//                "olarualina01@gmail.com",
//                "0726080518",
//                true,
//                true,
//                true,
//                "Romania",
//                "Bacau",
//                "Bacau",
//                "Calea Moldovei",
//                179,
//                "Happy ",
//                2,
//                57,
//                "parola",
//                "Username",
//                new HashSet<PersonalBook>(),
//                new HashSet<Wishlist>(),
//                new HashSet<BookOrder>(),
//                new HashSet<Complaint>(),
//                new HashSet<Review>(),
//                new HashSet<VoucherUser>()
//        );
//
//       this.bookUserRepository.save(b1);
//        this.publisherRepository.save(new Publisher(
//                "Test"
//        ));
        return this.bookUserRepository.findAll();
    }
}
