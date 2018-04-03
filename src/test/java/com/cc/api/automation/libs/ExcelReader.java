package com.cc.api.automation.libs;

public class ExcelReader
{
/*
	static File file=new File("F:\\auto\\GenesisUIAutomation\\src\\test\\resources\\files\\ConfigSheet.xlsx");
    public static HashMap loadExcelLines()
    {
        // Used the LinkedHashMap and LikedList to maintain the order
        HashMap<String, LinkedHashMap<Integer, List>> outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List>>();

        LinkedHashMap<Integer, List> hashMap = new LinkedHashMap<Integer, List>();

        String sheetName = null;
        // Create an ArrayList to store the data read from excel sheet.
        // List sheetData = new ArrayList();
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
            // Create an excel workbook from the file system
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            // Get the first sheet on the workbook.
            for (int i = 0; i < workBook.getNumberOfSheets(); i++)
            {
                XSSFSheet sheet = workBook.getSheetAt(i);
                // XSSFSheet sheet = workBook.getSheetAt(0);
                sheetName = workBook.getSheetName(i);

                Iterator rows = sheet.rowIterator();
                while (rows.hasNext())
                {
                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator cells = row.cellIterator();

                    List data = new LinkedList();
                    while (cells.hasNext())
                    {
                        XSSFCell cell = (XSSFCell) cells.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        data.add(cell);
                    }
                    hashMap.put(row.getRowNum(), data);

                    // sheetData.add(data);
                }
                outerMap.put(sheetName, hashMap);
                hashMap = new LinkedHashMap<Integer, List>();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return outerMap;

    }
	public void readExcel()
	{
		Fillo fillo=new Fillo();
		Connection connection = null;
		try {
		//	File file=new File("F:\\auto\\GenesisUIAutomation\\src\\test\\resources\\files\\ConfigSheet.xlsx");
			connection = fillo.getConnection("F:\\auto\\GenesisUIAutomation\\src\\test\\resources\\files\\ConfigSheet.xlsx");
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strQuery="Select * from Sheet ";
		Recordset recordset = null;
		try {
			recordset = connection.executeQuery(strQuery);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			while(recordset.next()){
				HashMap<String, String> dataMap = new HashMap<String, String>();
				
				String key = recordset.getField("key");
				String value = recordset.getField("value");
			//	dataMap.put(key, value);
				System.out.println(recordset.getField("key"));
				System.out.println(recordset.getField("value"));
				//String value = recordset.getString("value");
				dataMap.put(key, value);
		//	System.out.println(recordset.getField("key"));
			System.out.println(dataMap.get("login3"));
			
			
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		recordset.close();
		connection.close();
	}
	
	
	public static void main(String args[])
	{
		FilloExcelUtility rl=new FilloExcelUtility();
		rl.readExcel();
	}*/
}

	
	

