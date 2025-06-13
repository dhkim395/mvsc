<Context>
    <!-- JNDI 이름 정의 -->
    <Resource name="jdbc/mydb"
              auth="Container"
              type="javax.sql.DataSource"
              maxTotal="20"
              maxIdle="10"
              maxWaitMillis="-1"
              username="root"
              password="1234"
              driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/java?serverTimezone=Asia/Seoul"/>
</Context>