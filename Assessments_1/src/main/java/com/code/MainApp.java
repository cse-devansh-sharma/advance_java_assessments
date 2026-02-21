package com.code;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainApp{
		    
		    public static void main(String[] args) {
		        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeIDCard");
		        EntityManager em = emf.createEntityManager();
		        
		            em.getTransaction().begin();
		            
		            Employee emp = new Employee("Devansh", "Devanshsharmma009@gmail.com");
		            
		            IDCard id = new IDCard("ID101", "14-02-2026");
		            
		            emp.setIdCard(id);
		            id.setEmployee(emp);
		            
		            em.persist(emp);
		            
		            em.getTransaction().commit();
		            
		            System.out.println("Employee Created Successfully");
		            System.out.println();
		            
		            em.clear();
		            
		            em.getTransaction().begin();
		            
		            Employee findEmp = em.find(Employee.class, emp.getId());
		            
		            if (findEmp != null) {
		                System.out.println("Employee Details:");
		                System.out.println("ID: " + findEmp.getId());
		                System.out.println("Name: " + findEmp.getName());
		                System.out.println("Email: " + findEmp.getEmail());
		                System.out.println();
		                
		                if (findEmp.getIdCard() != null) {
		                    System.out.println("ID Card Details:");
		                    System.out.println("Card Number: " + findEmp.getIdCard().getCardNumber());
		                    System.out.println("Issue Date: " + findEmp.getIdCard().getIssueDate());
		                }
		            }
		            
		            em.getTransaction().commit();
		            em.close();
		            emf.close();
		            
		    }
}
