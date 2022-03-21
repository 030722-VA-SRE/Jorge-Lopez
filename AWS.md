### Practicals

Given the following table 'employees'...

| id | firstName | lastName | salary | dept |
| --- | -------- | -------- | ------ | ---- |
| 1  | Michael   | Scott    | 65     | Sales|
| 2  | Dwight    | Schrute  | 75     | Sales|
| 3  | Toby      | Flenderson| 80    | HR  |
| 4  | Jim       | Halpert  | 90     | Sales|
| 5  | Oscar     | Martinez | 90     | Accounting |
| 6  | Angela    | Martin   | 75     | Accounting |
| 7  | Kevin     | Malone   | 70     | Accounting |
| 8  | Holly     | Flax     | 60     | HR |
| 9  | Creed     | Branton  | 70     | Quality Assurance |

* Write a query to find all data in the table
** select * from employees;

* Write a query to find employees with a salary over 75
*  select * from employees where salary > 75;

* Write a query to find employees whose first name contains an 'e' or whose last name begins with 'S'\
*  select firstName from employees where firstName like '%e%' or lastName like 'S%';

* Write a query to find the first name of all employees who do not work in accounting
*  select firstName from employees where dept not like 'Accounting';

* Write a query to find the average salary of all employees whose last names begin with 'M'
* select avg(salary) from employees where lastName like 'M%';

* Write a query to find the highest paid salesperson
* select max(salary) from employees where dept = 'Sales';

* Write a query to combine the resultsets of any two previous queries
* select firstName from employees where dept != Accounting union select firstName from employees where salary > 75;

* Remove all members of accounting from the database
* delete from employees where dept = Accounting;

* Given removing the dept column from the employees table and creating a table 'department' and linking the two via a foreign key relationship

| dept_id | name |
| ------- | ---  |
| 1       | Sales |
| 2       | HR   |
| 3       | Accounting |
| 4       | Customer Service |

* Write a query to find the salary of the lowest paid salesperson (HINT: use a join)
* select min(employees.salary) from employees join department on employees.dept = dept.dept_id;

* Write a query to find the average salary of each department
* select avg(employees.salary), distinct(employees.dept) from department join employees on dept.name = dept.dept_id;

* Write a query to find all possible combinations of employees and departments. How many records do you expect?


* Change the name of department 4 to 'Quality Assurance'
* update dept set name = 'Quality Assurance' where dept_id = 4;

* Remove both tables
* drop table employees;
* drop table dept;

# JDBC
1.	What is JDBC?
* JDBC stands for Java Database Connectivity, which allows us to connect our Java application and a database.
2.	What are the core interfaces / classes in JDBC?
* The major interfaces in JDBC are DriverManager, Connection, PreparedStatement, ResultSet and Statement.
3.	What is a stored procedure and how would you call it in Java?

4.	What is the difference between Statement and PreparedStatement?
* Statement, can be a string that, represents a SQL statement, but is vulnerable to SQL injection therefore the more safer option is
to use PreparedStatement. Main difference is that PreparedStatement has been preprocessed to prevent SQL injection by using methods to
set fields.

5.	Steps to executing an SQL query using JDBC?
* 1st: Establish connection to database via DriverManager and Connection classes.
* 2nd: Create a Statement or PreparedStatement with your SQL statement 
* 3rd: Execute Query and store result in ResultSet object
* 4th: Print result, if any via ResultSet. This can include looping through if there are mutliple results from given query.
* 5th: Make sure to close connection with database after results, to not exhaust your database and bandwith.

# AWS

### Cloud / AWS Overview
* How would you describe AWS? What is "the cloud" or "cloud computing" and why is it so popular now?
AWS stands for Amazon Web Services, which is just various services that are ran in the cloud. Cloud computing provides fast and on demand computing power, database storage and other technical resources using the cloud via the internet. This is very popular because it allows businesses to expand and scaleback if needed, while keeping fast speeds and reliability. This meaning that they only pay for the services that AWS is providing.

* Define Infrastructure, Platform, and Software as a Service
Infrastructure as a Service: Self service model that manages remote data center infrastructure.  
Platform as a Service: Allows businesses/organizations to build, run and manage applications without IT infrastructure. This makes development, testing and deployment of an application a lot faster.
Software as a Service: Replaces the traditional on device software that is licensed on a subscription basis. It is centrally hosted in the cloud. (i.e: Google Suite, Google Docs)
 
* What's the difference between a Region and an Availability Zone (AZ)?
 A region is a geographical location with a collection of Availability zones that map to physical data centers. An Availability zone is a physical logical center within the available region. 

* How are you charged for using AWS services? Does it vary by service?
 Another reason AWS is more cost effective is because businesses only pay for the amount of services they use and the amount of compuiting power they use from said services. Therefore it can be vary by the service used and the amount of time they are used.  

* Different ways to interact with AWS services?
When it comes to interacting with AWS and their services. One can use the AWS management console, as it provides more readable UI. Another method is using your commandline to access your database and/or EC2 instance, assuming the proper security configurations have been applied.

### EC2

* What are the configuration options for EC2?
 EC2 is a web service that provides secure and for the ability to resize the computing capacity in the cloud. This is a virtual computing environment. They are also referred to as instances and they are able to be configured in various ways due to the CPU, memory, storage, and networking capacity of the instance, creating instance types.

* What are the different EC2 instance sizes/types?
 Mac: 6 physical/ 12 logical cores, 32 GB of RAM, Network BW: 10 Gb/s
 T4g: 2 - 8 vCPU, 0.5Gib- 32Gib RAM, Network BW: Up to 5 Gb/s
 T3: 2 - 8 vCPU, 0.5Gib - 32Gib RAM, Network BW: Up to 5 Gb/s
 T3a: 2,4,8 vCPU,  0.5Gib - 32Gib RAM, Network BW: Up to 5 Gb/s
 T2: 1,2,4,8 vCPU, 0.5Gib - 32Gib RAM, Network BW: Low to Moderate
 M6g: 1 - 64 vCPU, 4 - 256 Gib RAM, Network BW: 10 Gb/s - 25 Gb/s
 M6I: 2 - 128 vCPU, 8 - 512 Gib RAM, Network BW: 12.5 Gb/s - 50 Gb/s
 M6a: 2 - 193 vCPU, 8 - 768 Gib RAM, Network BW: 12.5 Gb/s - 50 Gb/s
 M5: 2 - 96 vCPU, 8 - 384 Gib RAM, Network BW: 10 Gb/s - 25 Gb/s
 M5a: 2 - 96 vCPU, 8 - 384 Gib RAM, Network BW: 10 Gb/s - 20 Gb/s
 M5n: 2 - 96 vCPU,  8 - 384 Gib RAM, Network BW: 25 Gb/s - 100 Gb/s
 M5zn: 2 - 48 vCPU, 8 - 192 Gib RAM, Network BW: 25 Gb/s - 100 Gb/s
 M4: 2 - 64 vCPU, 8 - 256 Gib RAM, Network BW: Moderate - 25 Gb/s
 A1: 1 - 16 vCPU, 2 - 32 Gib RAM,  Network BW: Up to 1o Gb/s 

* Once you create an EC2, how to connect to it?
 Once created, it is important that the proper security groups have been configured properly in order to access your instance as the root user. This can be done in the Security Group tab, this is where the settings are changed so that you can acess your VM via your local machine using SSH(Secure Shell) in your terminal.

 Command: ssh -i [file.pem] ec2-user@[address]

* What are Security Groups? When defining a rule for a security group, what 3 things do you need to specify?
  Security Group(s) acts as a virtual firewall for your EC2 instance. Security Groups enable you to filter traffic based on ports and IP address. Therefore when setting up your sscurity group it is important that a port is defined, IP address matches local machine as well as define the inbound and outbound rules for the data flowing. 

* What's the difference between scalability, elasticity, and resiliency?
 Scalabilty is the ability to grow up or down in size based on demand. Elasticity is similar to the scalabilty because it allows developers to instantly scale to meet spikes in traffic or demand. These two are very similar but the key difference is that elasticity allows demand to be met faster. This is assuming autoscalability has not been configured. Resiliency is the ability for a particular application or service to be reachable no matter how intense the data traffic can get. 

* Ways of paying for EC2?
On Demand: Pay for the instance your using by the second, no long term commitment
Saving plan: Reduce price by making a commitment to a consistent amount of usage for 1-3  years   
Reserved Instances: Commitment to a a specific instance configuration, this meaning it has to be the same instancy type and Region for a term of 1-3 years


### RDS

* What's an RDS?
A RDS stands for a Relational Database Service, which is a web service that makes it easier to setup, scale and operate a relational database. RDS automates tasks like managing backups, failure detection and recovery. They also make accesibility more efficient by allowing who can access and change the data.

* Which vendors are supported?
MySQL, MariaDB, Oracle, Microsoft SQL Service, PostgresSQL, and Amazon Aurora.

