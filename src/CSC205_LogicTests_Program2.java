import java.io.PrintStream;
import java.lang.reflect.*;

public class CSC205_LogicTests_Program2 {
    static int pass = 0;
    static int fail = 0;
    
    public static void main(String[] args) throws Exception {
        System.out.println("========== SavingsAccount Class Logic ==========");
        testSavingsAccountLogic();
        System.out.println("========== CheckingAccount Class Logic ==========");
        testCheckingAccountLogic();
        System.out.println("========== CredcardAccount Class Logic ==========");
        testCreditcardAccountLogic();
        System.out.println("-------------------------------------------------");
        System.out.println("Passed: " + pass);
        System.out.println("Failed: " + fail);
        System.out.println("        --------");
        System.out.printf ("        %.2f %% correct\n", (float)pass / (fail + pass) * 100);
        System.out.println("-------------------------------------------------");
      }

    private static void testSavingsAccountLogic() throws Exception {
        // Default constructor initializes accountNumber to "0000-0000-0000-0000"
        SavingsAccount acc = new SavingsAccount();
        String expectedAccountNumber = "0000-0000-0000-0000";
        String actualAccountNumber = (String) getBaseFieldValue(acc, "accountNumber");
        if (assertEqual(System.out, expectedAccountNumber, actualAccountNumber,
                    "Default constructor initializes accountNumber to \"0000-0000-0000-0000\""))
            pass++;
        else
            fail++;
        // Default constructor initializes balance to 0
        int expectedBalance = 0;
        int actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Default constructor initializes balance to 0"))
            pass++;
        else
            fail++;
        // Default constructor initializes interestRate to 0.0
        double expectedInterestRate = 0;
        double actualInterestRate = (double) getBaseFieldValue(acc, "interestRate");
        if (assertEqual(System.out, expectedInterestRate, actualInterestRate,
                "Default constructor initializes interestRate to 0.0"))
            pass++;
        else
            fail++;
        
        // After setAccountNumber("111-222-333-444"), getAccountNumber() returns "111-222-333-444"
        acc.setAccountNumber("111-222-333-444");
        expectedAccountNumber = "111-222-333-444";
        actualAccountNumber = acc.getAccountNumber();
        if (assertEqual(System.out, expectedAccountNumber, actualAccountNumber,
                    "After setAccountNumber(\"111-222-333-444\"), getAccountNumber() returns \"111-222-333-444\""))
            pass++;
        else
            fail++;
        
        // After setInterestRate(0.123), getInterestRate() returns 0.123
        acc.setInterestRate(0.123);
        expectedInterestRate = 0.123;
        double delta = 0.001;
        actualInterestRate = acc.getInterestRate();
        if (assertApprox(System.out, expectedInterestRate, actualInterestRate, delta,
                    "After setInterestRate(0.123), getInterestRate() returns 0.123"))
            pass++;
        else
            fail++;
        
        // Given initial balance of 0, and a credit(100), getBalance returns 100
        acc.credit(100);
        expectedBalance = 100;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Given initial balance of 0, and a credit(100), getBalance returns 100"))
            pass++;
        else
            fail++;
        
        // from current balance of 100, and a credit(1234), getBalance returns 1334
        acc.credit(1234);
        expectedBalance = 1334;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 100, and a credit(1234), getBalance returns 1334"))
            pass++;
        else
            fail++;
        
        // from current balance of 1334, debit(100) returns true
        boolean expectedDebitReturnVal = true;
        boolean actualDebitReturnVal = acc.debit(100);
        if (assertEqual(System.out, expectedDebitReturnVal, actualDebitReturnVal,
                "from current balance of 1334, debit(100) returns true"))
            pass++;
        else
            fail++;
        // from current balance of 1334, after debit(100), getBalance returns 1234
        expectedBalance = 1234;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 1334, after debit(100), getBalance returns 1234"))
            pass++;
        else
            fail++;
        
        // from current balance of 1234, and a debit(234), getBalance returns 1000
        acc.debit(234);
        expectedBalance = 1000;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 1234, and a debit(234), getBalance returns 1000"))
            pass++;
        else
            fail++;
        
        // from current balance of 1000, debit(2000) returns false
        expectedDebitReturnVal = false;
        actualDebitReturnVal = acc.debit(2000);
        if (assertEqual(System.out, expectedDebitReturnVal, actualDebitReturnVal,
                "from current balance of 1000, debit(2000) returns false"))
            pass++;
        else
            fail++;
        // from current balance of 1000, after debit(2000), getBalance returns 1000 
        expectedBalance = 1000;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 1000, after debit(2000), getBalance returns 1000"))
            pass++;
        else
            fail++;
        
        // with current balance of 1000, and interestRate of 0.123, and applyInterest(), getBalance returns 1123
        acc.applyInterest();
        expectedBalance = 1123;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "with current balance of 1000, and interestRate of 0.123, and applyInterest(), getBalance returns 1123"))
            pass++;
        else
            fail++;
        
        // with current balance of 1123, and interestRate of 0.123, and applyInterest(), getBalance returns 1261
        acc.applyInterest();
        expectedBalance = 1261;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "with current balance of 1123, and interestRate of 0.123, and applyInterest(), getBalance returns 1261"))
            pass++;
        else
            fail++;
        
        // with accountNumber "111-222-333-444", balance 1261, and interestRate  0.123, getAccountInfo returns correct string
        String expectedInfo = "Account type  : Savings\n" +
                              "Account #     : 111-222-333-444\n" +
                              "Balance       : $12.61\n" +
                              "Interest rate : 12.30%";
        String actualInfo = acc.getAccountInfo().trim();
        if (assertEqualText(System.out, expectedInfo.replace(' ', '�').replace('\t', '�'), actualInfo.replace(' ', '�').replace('\t', '�'),
                "with accountNumber \"111-222-333-444\", balance 1261, and interestRate  0.123, getAccountInfo returns correct string"))
            pass++;
        else {
            fail++; 
            System.out.println("NOTE: in the above text, spaces have been preplacedwith '�'");
            System.out.println("                       and tabs have been replaced with '�'");
            System.out.println("                       so that you can see them.");
        } 
    }

    private static void testCheckingAccountLogic() throws Exception {
     // Default constructor initializes accountNumber to "0000-0000-0000-0000"
        CheckingAccount acc = new CheckingAccount();
        String expectedAccountNumber = "0000-0000-0000-0000";
        String actualAccountNumber = (String) getBaseFieldValue(acc, "accountNumber");
        if (assertEqual(System.out, expectedAccountNumber, actualAccountNumber,
                    "Default constructor initializes accountNumber to \"0000-0000-0000-0000\""))
            pass++;
        else
            fail++;
        // Default constructor initializes balance to 0
        int expectedBalance = 0;
        int actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Default constructor initializes balance to 0"))
            pass++;
        else
            fail++;
        // Default constructor initializes interestRate to 0.0
        double expectedInterestRate = 0;
        double actualInterestRate = (double) getBaseFieldValue(acc, "interestRate");
        if (assertEqual(System.out, expectedInterestRate, actualInterestRate,
                "Default constructor initializes interestRate to 0.0"))
            pass++;
        else
            fail++;
        // Default constructor initializes overdraftFee to 0
        int expectedOverdraftFee = 0;
        int actualOverdraftFee = (int) getDeclaredFieldValue(acc, "overdraftFee");
        if (assertEqual(System.out, expectedOverdraftFee, actualOverdraftFee,
                "Default constructor initializes overdraftFee to 0"))
            pass++;
        else
            fail++;
        
        // After setAccountNumber("111-222-333-444"), getAccountNumber() returns "111-222-333-444"
        acc.setAccountNumber("444-333-222-111");
        expectedAccountNumber = "444-333-222-111";
        actualAccountNumber = acc.getAccountNumber();
        if (assertEqual(System.out, expectedAccountNumber, actualAccountNumber,
                    "After setAccountNumber(\"444-333-222-111\"), getAccountNumber() returns \"444-333-222-111\""))
            pass++;
        else
            fail++;
        
        // After setInterestRate(0.123), getInterestRate() returns 0.123
        acc.setInterestRate(0.123);
        expectedInterestRate = 0.123;
        double delta = 0.001;
        actualInterestRate = acc.getInterestRate();
        if (assertApprox(System.out, expectedInterestRate, actualInterestRate, delta,
                    "After setInterestRate(0.123), getInterestRate() returns 0.123"))
            pass++;
        else
            fail++;
        
        // After setOverdraftFee(2000), getOverdraftFee() returns 2000
        acc.setOverdraftFee(2000);
        expectedOverdraftFee = 2000;
        actualOverdraftFee = acc.getOverdraftFee();
        if (assertEqual(System.out, expectedOverdraftFee, actualOverdraftFee,
                    "After setOverdraftFee(2000), getOverdraftFee() returns 2000"))
            pass++;
        else
            fail++;
        
        // Given initial balance of 0, and a credit(100), getBalance returns 100
        acc.credit(100);
        expectedBalance = 100;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Given initial balance of 0, and a credit(100), getBalance returns 100"))
            pass++;
        else
            fail++;
        
        // from current balance of 100, and a credit(1234), getBalance returns 1334
        acc.credit(1234);
        expectedBalance = 1334;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 100, and a credit(1234), getBalance returns 1334"))
            pass++;
        else
            fail++;
        
        // from current balance of 1334, debit(100) returns true
        boolean expectedDebitReturnVal = true;
        boolean actualDebitReturnVal = acc.debit(100);
        if (assertEqual(System.out, expectedDebitReturnVal, actualDebitReturnVal,
                "from current balance of 1334, debit(100) returns true"))
            pass++;
        else
            fail++;
        // from current balance of 1334, after debit(100), getBalance returns 1234
        expectedBalance = 1234;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 1334, after debit(100), getBalance returns 1234"))
            pass++;
        else
            fail++;
        
        // from current balance of 1234, and a debit(234), getBalance returns 1000
        acc.debit(234);
        expectedBalance = 1000;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 1234, and a debit(234), getBalance returns 1000"))
            pass++;
        else
            fail++;
        
        // from current balance of 1000, debit(2000) returns true
        expectedDebitReturnVal = true;
        actualDebitReturnVal = acc.debit(2000);
        if (assertEqual(System.out, expectedDebitReturnVal, actualDebitReturnVal,
                "from current balance of 1000, debit(2000) returns true"))
            pass++;
        else
            fail++;
        // from current balance of 1000 and overdraftFee of 2000, after debit(2000), getBalance returns -3000 
        expectedBalance = -3000;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of 1000 and overdraftFee of 2000, after debit(2000), getBalance returns -3000 "))
            pass++;
        else
            fail++;
        
        // with current balance of -3000, and interestRate of 0.123, and applyInterest(), getBalance returns -3000
        acc.applyInterest();
        expectedBalance = -3000;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "with current balance of -3000, and interestRate of 0.123, and applyInterest(), getBalance returns -3000"))
            pass++;
        else
            fail++;
        
        // with current balance of 1000, and interestRate of 0.123, and applyInterest(), getBalance returns 1123
        acc.credit(4000); // set balance to 1000
        acc.applyInterest();
        expectedBalance = 1123;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "with current balance of 1000, and interestRate of 0.123, and applyInterest(), getBalance returns 1123"))
            pass++;
        else
            fail++;
        
        // with current balance of 1123, and interestRate of 0.123, and applyInterest(), getBalance returns 1261
        acc.applyInterest();
        expectedBalance = 1261;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "with current balance of 1123, and interestRate of 0.123, and applyInterest(), getBalance returns 1261"))
            pass++;
        else
            fail++;
        
        // with accountNumber "444-333-222-111", balance 1261, and interestRate  0.123, getAccountInfo returns correct string
        String expectedInfo = "Account type  : Checking\n" +
                              "Account #     : 444-333-222-111\n" +
                              "Balance       : $12.61\n" +
                              "Interest rate : 12.30%\n" + 
                              "Overdraft fee : $20.00";
        String actualInfo = acc.getAccountInfo().trim();
        if (assertEqualText(System.out, expectedInfo.replace(' ', '�').replace('\t', '�'), actualInfo.replace(' ', '�').replace('\t', '�'),
                "with accountNumber \"111-222-333-444\", balance 1261, and interestRate  0.123, getAccountInfo returns correct string"))
            pass++;
        else {
            fail++; 
            System.out.println("NOTE: in the above text, spaces have been preplacedwith '�'");
            System.out.println("                       and tabs have been replaced with '�'");
            System.out.println("                       so that you can see them.");
        } 
    }

    private static void testCreditcardAccountLogic() throws Exception {
        // Default constructor initializes accountNumber to "0000-0000-0000-0000"
        CreditcardAccount acc = new CreditcardAccount();
        String expectedAccountNumber = "0000-0000-0000-0000";
        String actualAccountNumber = (String) getBaseFieldValue(acc, "accountNumber");
        if (assertEqual(System.out, expectedAccountNumber, actualAccountNumber,
                    "Default constructor initializes accountNumber to \"0000-0000-0000-0000\""))
            pass++;
        else
            fail++;
        // Default constructor initializes balance to 0
        int expectedBalance = 0;
        int actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Default constructor initializes balance to 0"))
            pass++;
        else
            fail++;
        // Default constructor initializes interestRate to 0.0
        double expectedInterestRate = 0;
        double actualInterestRate = (double) getBaseFieldValue(acc, "interestRate");
        if (assertEqual(System.out, expectedInterestRate, actualInterestRate,
                "Default constructor initializes interestRate to 0.0"))
            pass++;
        else
            fail++;
     // Default constructor initializes creditLimit to 0
        int expectedCreditLimit = 0;
        int actualCreditLimit = (int) getDeclaredFieldValue(acc, "creditLimit");
        if (assertEqual(System.out, expectedCreditLimit, actualCreditLimit,
                "Default constructor initializes creditLimit to 0"))
            pass++;
        else
            fail++;
        
        // After setAccountNumber("111-222-333-444"), getAccountNumber() returns "111-222-333-444"
        acc.setAccountNumber("111-222-333-444");
        expectedAccountNumber = "111-222-333-444";
        actualAccountNumber = acc.getAccountNumber();
        if (assertEqual(System.out, expectedAccountNumber, actualAccountNumber,
                    "After setAccountNumber(\"111-222-333-444\"), getAccountNumber() returns \"111-222-333-444\""))
            pass++;
        else
            fail++;
        
        // After setInterestRate(0.123), getInterestRate() returns 0.123
        acc.setInterestRate(0.123);
        expectedInterestRate = 0.123;
        double delta = 0.001;
        actualInterestRate = acc.getInterestRate();
        if (assertApprox(System.out, expectedInterestRate, actualInterestRate, delta,
                    "After setInterestRate(0.123), getInterestRate() returns 0.123"))
            pass++;
        else
            fail++;
        
        // After setCreditLimit(2000), getCreditLimit() returns 2000
        acc.setCreditLimit(2000);
        expectedCreditLimit = 2000;
        actualCreditLimit = acc.getCreditLimit();
        if (assertApprox(System.out, expectedInterestRate, actualInterestRate, delta,
                    "After setCreditLimit(2000), getCreditLimit() returns 2000"))
            pass++;
        else
            fail++;
        
        // Given initial balance of 0, creditLimit of 2000, debit(100) returns true
        boolean expectedDebitReturnVal = true;
        boolean actualDebitReturnVal = acc.debit(100);
        if (assertEqual(System.out, expectedDebitReturnVal, actualDebitReturnVal,
                "Given initial balance of 0, creditLimit of 2000, debit(100) returns true"))
            pass++;
        else
            fail++;
        // Given initial balance of 0, creditLimit of 2000, and a debit(100), getBalance returns -100
        expectedBalance = -100;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Given initial balance of 0, creditLimit of 2000, and a debit(100), getBalance returns -100"))
            pass++;
        else
            fail++;
        
        // Given balance of -100, creditLimit of 2000, and a debit(1000) returns true
        expectedDebitReturnVal = true;
        actualDebitReturnVal = acc.debit(1000);
        if (assertEqual(System.out, expectedDebitReturnVal, actualDebitReturnVal,
                "Given balance of -100, creditLimit of 2000, and a debit(1000) returns true"))
            pass++;
        else
            fail++;
        // Given balance of -100, creditLimit of 2000, and a debit(1000), getBalance returns -1100
        expectedBalance = -1100;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Given balance of -100, creditLimit of 2000, and a debit(1000), getBalance returns -1100"))
            pass++;
        else
            fail++;
        
        // Given balance of -1100, creditLimit of 2000, and a debit(1000) returns false
        expectedDebitReturnVal = false;
        actualDebitReturnVal = acc.debit(1000);
        if (assertEqual(System.out, expectedDebitReturnVal, actualDebitReturnVal,
                "Given balance of -1100, creditLimit of 2000, and a debit(1000) returns false"))
            pass++;
        else
            fail++;
        // Given balance of -1100, creditLimit of 2000, and a debit(1000), getBalance returns -1100
        expectedBalance = -1100;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "Given balance of -1100, creditLimit of 2000, and a debit(1000), getBalance returns -1100"))
            pass++;
        else
            fail++;
        
        // from current balance of -1100, and a credit(50), getBalance returns -1050
        acc.credit(50);
        expectedBalance = -1050;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of -1100, and a credit(50), getBalance returns -1050"))
            pass++;
        else
            fail++;
        
        // from current balance of -1050, and a credit(50), getBalance returns -1000
        acc.credit(50);
        expectedBalance = -1000;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "from current balance of -1050, and a credit(50), getBalance returns -1000"))
            pass++;
        else
            fail++;
            
        // with current balance of -1000, and interestRate of 0.123, and applyInterest(), getBalance returns -1123
        acc.applyInterest();
        expectedBalance = -1123;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "with current balance of -1000, and interestRate of 0.123, and applyInterest(), getBalance returns -1123"))
            pass++;
        else
            fail++;
        
        // with current balance of -1123, and interestRate of 0.123, and applyInterest(), getBalance returns -1261
        acc.applyInterest();
        expectedBalance = -1261;
        actualBalance = (int) getBaseFieldValue(acc, "balance");
        if (assertEqual(System.out, expectedBalance, actualBalance,
                "with current balance of -1123, and interestRate of 0.123, and applyInterest(), getBalance returns -1261"))
            pass++;
        else
            fail++;
        
        // with accountNumber "111-222-333-444", balance 1261, and interestRate  0.123, getAccountInfo returns correct string
        String expectedInfo = "Account type  : Creditcard\n" +
                              "Account #     : 111-222-333-444\n" +
                              "Balance       : $-12.61\n" +
                              "Interest rate : 12.30%\n" +
                              "Credit limit  : $20.00";
        String actualInfo = acc.getAccountInfo().trim();
        if (assertEqualText(System.out, expectedInfo.replace(' ', '�').replace('\t', '�'), actualInfo.replace(' ', '�').replace('\t', '�'),
                "with accountNumber \"111-222-333-444\", balance 1261, and interestRate  0.123, getAccountInfo returns correct string"))
            pass++;
        else {
            fail++; 
            System.out.println("NOTE: in the above text, spaces have been preplacedwith '�'");
            System.out.println("                       and tabs have been replaced with '�'");
            System.out.println("                       so that you can see them.");
        }  
    }
    
    private static boolean assertEqual(PrintStream out, int expected, int actual, String message) {
        if (expected == actual) {
            out.println("PASS - " + message);
            out.println();
            return true;
        } else {
            out.println("FAIL - " + message);
            out.printf ("!!!!   expected[%d] but got [%d]\n\n", expected, actual);
            return false;
        }
    }
    
    private static boolean assertEqual(PrintStream out, double expected, double actual, String message) {
        if (expected == actual) {
            out.println("PASS - " + message);
            out.println();
            return true;
        } else {
            out.println("FAIL - " + message);
            out.printf ("!!!!   expected[%f] but got [%f]\n\n", expected, actual);
            return false;
        }
    }
    
    private static boolean assertEqual(PrintStream out, String expected, String actual, String message) {
        if (expected.equals(actual)) {
            out.println("PASS - " + message);
            out.println();
            return true;
        } else {
            out.println("FAIL - " + message);
            out.printf ("!!!!   expected [%s] but got [%s]\n\n", expected, actual);
            return false;
        }
    }

    private static boolean assertEqualText(PrintStream out, String expected, String actual, String message) {
        if (expected.equals(actual)) {
            out.println("PASS - " + message);
            out.println();
            return true;
        } else {
            out.println("FAIL - " + message);
            out.printf ("!!!!   \n" +
                        "expected \n----------\n%s\n----------\n" +
                        "but got  \n----------\n%s\n----------\n",
                         expected, actual);
            return false;
        }
    }
    
    private static boolean assertEqual(PrintStream out, boolean expected, boolean actual, String message) {
        if (expected == actual) {
            out.println("PASS - " + message);
            out.println();
            return true;
        } else {
            out.println("FAIL - " + message);
            out.printf ("!!!!   expected[%b] but got [%b]\n\n", expected, actual);
            return false;
        }
    }
    
    private static boolean assertApprox(PrintStream out, double expected, double actual, double delta, String message) {
        if (Math.abs(expected - actual) <= delta) {
            out.println("PASS - " + message);
            out.println();
            return true;
        } else {
            out.println("FAIL - " + message);
            out.printf ("!!!!   expected[%f � %f]but got [%f]\n\n", expected, delta, actual);
            return false;
        }
    }
    
    private static Object getBaseFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
    
    private static Object getDeclaredFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

}