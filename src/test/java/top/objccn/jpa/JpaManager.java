package top.objccn.jpa;

import org.junit.Before;
import org.junit.Test;
import top.objccn.jap.entity.Customer;

import javax.persistence.*;
import java.util.List;

/**
 * @Auter MrDML
 * @Date 2019-11-12
 */
public class JpaManager {

    private EntityManagerFactory entityManagerFactory;


    @Before
    public void init(){
        entityManagerFactory = Persistence.createEntityManagerFactory("myjpa");
    }

    @Test
    public  void addCustomer(){

        // 创建EntityManager 对象
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 开启事务
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        for (int i = 0; i < 10; i++) {
            // 创建 Customer 对象
            Customer customer = new Customer();
            customer.setCustName("Mr_ROM_" +  i);
            customer.setCustAddress("北京");
            customer.setCustLevel("VIP");
            customer.setCustIndustry("what");
            customer.setCustSource("IT");
            customer.setCustPhone("1380803194" + i);

            // 插入数据
            entityManager.persist(customer);
        }

        // 提交事务
        transaction.commit();
        // 关闭连接
        entityManager.clear();

    }


    @Test
    public void deleteCustomer(){

        // 1. 获得EntityManager 对象
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 2. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        // 3. 先从数据库中查询一个Customer对象
        /**find方法根据主键查询
         *  - 参数一:返回结果的数据类型,
         *  - 参数二:主键的值
         */
        Customer customer = entityManager.find(Customer.class, 1l);
        // 4. 使用entityManager对象的remove方法删除, 参数就是Customer对象
        entityManager.remove(customer);

        // 5.提交事务
        transaction.commit();

        // 6. 关闭连接
        entityManager.close();

    }

    @Test
    public  void updateCustomer(){
        // 1. 获得EntityManager 对象
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 2. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 3. 开启事务
        transaction.begin();

        // 4. 根据id查询一个Customer对象
        Customer customer = entityManager.find(Customer.class, 2l);
        // 5. 修改对象的属性
        customer.setCustLevel("超级VIP");
        customer.setCustIndustry("IT6666");
        customer.setCustSource("互联网");
        // 6. 把修改结果保存到数据库(合并)
        entityManager.merge(customer);

        // 7. 提交事务
        transaction.commit();
        // 8. 关闭连接
        entityManager.close();

    }


    /**
     * 通过 id 查询对象
     */
    @Test
    public  void findById(){
        // 1. 创建一个EntityManager对象
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 2. 查询不需要开启事务
        Customer customer = entityManager.find(Customer.class, 2l);
        System.out.println("============================");
        // 3. 打印查询对象
        System.out.println(customer);
        System.out.println("============================");
        // 3. 关闭连接
        entityManager.close();

    }


    /**
     * 通过id查询对象
     */
    @Test
    public void  findById_Other(){

        // 1. 创建一个EntityManger对象
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 2. 查询对象
        Customer customer = entityManager.getReference(Customer.class, 2l);
        System.out.println("============================");
        // 3. 打印查询对象
        System.out.println(customer);
        System.out.println("============================");
        // 3. 关闭连接
        entityManager.close();


    }


    @Test
    public void findAll(){

        // 1. 获取一个实例对象EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 2. 建立查询  select * from cst_customer
        Query query = entityManager.createQuery("from Customer");

        // 3. 获取查询结果
        List<Customer> resultList = query.getResultList();

        // 4. 遍历查询结果
        for (Customer customer : resultList) {
            System.out.println(customer);
        }

        // 5. 关闭连接
        entityManager.close();

    }

    @Test
    public void findAllWithPage(){
        //1.创建一个EntiyManager 对象
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //2. 创建一个query对象,查询全部数据
        Query query = entityManager.createQuery("from Customer");
        //3. 设置分页信息
        query.setFirstResult(5);
        query.setMaxResults(5);
        //4. 执行查询
        List<Customer> resultList = query.getResultList();
        //5. 打印结果
        for (Customer customer : resultList) {
            System.out.println(customer);
        }
        //6. 关闭连接
        entityManager.close();


    }

    @Test
    public void findByParamId_A(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from Customer where custId = ?");
        // 设置查询条件
        // 设置参数,索引应该是从1开始
        query.setParameter(1,2l);
        // 执行查询
        Object customer = query.getSingleResult();
        System.out.println(customer);
        entityManager.close();

    }

    @Test
    public void findByName(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("from Customer where custName like ? and custAddress like ?");

        // 设置查询条件
        query.setParameter(1, "%Mr_ROM_3%");
        query.setParameter(2, "%北京%");

        List<Customer> list = query.getResultList();

        for (Customer customer : list) {
            System.out.println(customer);
        }

        entityManager.close();


    }

    @Test
    public void findAllWithOrder(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from Customer order by custId asc");

        List<Customer> resultList = query.getResultList();

        for (Customer customer : resultList) {

            System.out.println(customer);
        }

        entityManager.close();
    }

    @Test
    public  void testCount(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Query query = entityManager.createQuery("select count(*) from Customer");


        Object singleResult = query.getSingleResult();

        System.out.println("总条目..." + singleResult);

        entityManager.close();
    }

















}
