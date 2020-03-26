package com.alina.mylibrary;

import com.alina.mylibrary.model.DBInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MylibraryApplication {

	public static void main(String[] args) {

		SpringApplication.run(MylibraryApplication.class, args);
	}
/*


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory()) {
            CourseDao courseDao = new CourseDao(sessionFactory);
            TeacherDao teacherDao = new TeacherDao(sessionFactory);
            try (Session session = sessionFactory.getCurrentSession()) {
                Transaction tx = session.beginTransaction();
                // create teachers
                Teacher pj = teacherDao.save(new Teacher("Profesor Jirafales","https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Ruben2017.jpg/245px-Ruben2017.jpg","jirafales@example.com"));
                Teacher px = teacherDao.save(new Teacher("Professor X","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9uI1Cb-nQ2uJOph4_t96KRvLSMjczAKnHLJYi1nqWXagvqWc4","director@xproject_.com"));
                courseDao.save(new Course("Mathematics", 20, 10, pj));
                courseDao.save(new Course("Spanish", 20, 10, pj));
                courseDao.save(new Course("Dealing with unknown", 10, 100, px));
                courseDao.save(new Course("Handling your mental power", 50, 100, px));
                courseDao.save(new Course("Introduction to psychology", 90, 100, px));
                tx.commit();
            }
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                System.out.println("Courses");
                courseDao.list().forEach(course -> System.out.println(course.getName()));
                System.out.println("Teachers");
                teacherDao.list().forEach(teacher -> System.out.println(teacher.getName()));
            }
        }
    }
}



 */
}
