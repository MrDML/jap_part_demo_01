package top.objccn.jpa;

import org.junit.Test;
import top.objccn.jap.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Auter MrDML
 * @Date 2019-11-12
 */
public class JpaTest {



    @Test
    public void firstTest(){
        // 1. 创建一个EntityManagerFactory对象, 在系统一般都是单例的
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myjpa");
        // 2. 使用工厂对象创建一个EntityManager对象. 一个EntityManager就是一个连接. 是一个多例对象,使用完毕之后就关闭
        EntityManager entityManager = factory.createEntityManager();
        // 3. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        // 4. 创建一个Customer对象
        Customer customer = new Customer();
        customer.setCustName("Mr_Rom");
        customer.setCustLevel("vip");
        customer.setCustSource("IT");
        customer.setCustPhone("1380803194");
        customer.setCustAddress("上海");

        // 5. 使用Entitymanager对象的persist方法想数据库插入数据
        entityManager.persist(customer);
        // 6. 事务提交
        transaction.commit();
        // 7. 关闭连接
        entityManager.close();
        // 系统关闭之前关闭工厂对象
        factory.close();
    }




















}
