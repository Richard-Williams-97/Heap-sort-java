import java.io.File;
import java.util.Scanner;

/**
* File header.
*
* Copyright notice goes here.
*/

/**
* This Java application reads records from an input file and sort
* them using heapsort. Intermediate results are output to check
* if the sorting is done correctly.
*
* @author Some One
*
*/
public class HeapSortApp {
/**
* Private student record class.
* @author Some one.
*
*/
private static class StudentRecord 
{
	private int id;
	private String name;
/**
* Constructor
* @param aID
* @param aName
*/
public StudentRecord(int aID, String aName) 
{
	id = aID;
	name = aName;
}

	@Override
	public String toString() 
	{
		return "(" + id + ", " + name + ")";
	}
}
/**
* @param args
*/
public static void main(String[] args) throws Exception 
{
// read from input file -- name is given by the first argument
// File format:
// 33 <-- number of records
// 1 John Smith <-- one student record (id and name)
// 2 Jane Fonda <-- any number of whitespace in between id and name
// 3 Vincent Foxx
// ... ...
StudentRecord[] records = readRecordsFromFile(args[0]);
printRecords(records, "Records from file:");
// turn the input array of records into a heap (in-place)
heapify(records, 0);
printRecords(records, "Heapified result:");
// sort the heapified array into sorted array
heapsort(records);
printRecords(records, "Sorted result:");
}


/**
* This method sorts the heapified array of records by
* consecutive extraction.
* @param records
*/
private static void heapsort(StudentRecord[] records) 
{
// WARNING off-by-error will easily "kill" you!!
// TODO
		int l=records.length;
		
		for(int i=l/2; i>=1;i--)
		heapify(records, i);
		
	for(int i=l-1; i>=2; i--)
	{
		StudentRecord a=records[0];
		records[0]=records[i];
		records[i]=a;
		
		
		heapify(records,1);
	}
}

/**
* This method builds a heap in place of the input array of records
* by sifting up.
*
* @param records Student record array.
*/
private static void heapify(StudentRecord[] records, int i) 
{ 
// WARNING off-by-error will easily "kill" you!!
// TODO
	
	int large=i;
	int l=2*i;
	int r=2*i+1;
	
	if(l<records.length && records[l].id>records[i].id)
	large=l;
	
	if(r<records.length && records[r].id>records[large].id)
	large=r;
	
	if(large!=i)
	{
		StudentRecord save=records[large];
		records[large]=records[i];
		records[i]=save;
		heapify(records,large);
	}
}

private static StudentRecord[] readRecordsFromFile(String fileName)
		throws Exception {

	Scanner sc = new Scanner(new File(fileName));
	int nRecords; // number of records
	int studentID;
	String studentName;
	StudentRecord[] records;

	// read number of records using scanner
	nRecords = sc.nextInt();
	records = new StudentRecord[nRecords];

	// read each record using scanner
	for (int i=0; i<nRecords; i++) 
	{

		// read id
		studentID = sc.nextInt();

		// Note a name can contain multiple words -- which method do you use??
		// If you read a line, you need to trim the leading and trailing spaces
		studentName = sc.nextLine().trim();
		
		//System.out.println(studentID+studentName);
		records[i] = new StudentRecord(studentID, studentName);
	}

	// finish using scanner
	sc.close();

	return records;
}


private static void printRecords(StudentRecord[] recs, String hdr) 
{
	int i = 0;
	System.out.print(hdr + "\n\t[ ");
	for (StudentRecord r: recs) 
	{
		System.out.print(r + " ");
		i++;
		
		if (3 == i) 
		{
			System.out.println();
			System.out.print("\t ");
			i = 0;
		}
	}
System.out.println("]");
}

}