package com.spacey.effective.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TableRelation {

	public static void main(String[] args) {
		ArrayList<Table> tablesList = new ArrayList<Table>();
//
//		tableColumnDetails
//

		Table employeTable = new Table("Employee");
		employeTable.addColumn(new Column("EmployeeName", false, null));
				employeTable.addColumn(new Column("EmployeeAge", false, null));
						employeTable.addColumn(new Column("EmployeeLocation", true, new ForeignKeyRelation("Location","LocationName")));
								employeTable.addColumn(new Column("AssiagnedVehicle", true, new ForeignKeyRelation("Vechicles","VehicleName")));
										employeTable.addColumn(new Column("EmployeeDepartment", true, new ForeignKeyRelation("Department","DepartmentName")));
		Table locationTable = new Table("Location");
				locationTable.addColumn(new Column("LocationName", false, null));
						locationTable.addColumn(new Column("LocationPincode", false, null));
								locationTable.addColumn(new Column("LocationAdmin", true, new ForeignKeyRelation("Employee","EmployeeName")));
		Table departmentTable = new Table("Department");
				departmentTable.addColumn(new Column("DepartmentName", false, null));
						departmentTable.addColumn(new Column("DepartmentRevenue", false, null));
								departmentTable.addColumn(new Column("DepartmentHead", true, new ForeignKeyRelation("Employee","EmployeeName")));
		Table productTable = new Table("Product");
				productTable.addColumn(new Column("ProductName", false, null));
						productTable.addColumn(new Column("ProductUnitPrice", false, null));
								productTable.addColumn(new Column("ProductStock", false, null));
		Table ordersTable = new Table("Orders");
				ordersTable.addColumn(new Column("OrderProduct",true, new ForeignKeyRelation("Product","ProductName")));
						ordersTable.addColumn(new Column("OrderQuantity", false, null));
								ordersTable.addColumn(new Column("OrderBy",  true, new ForeignKeyRelation("Employee","EmployeeName")));
		Table vechiclesTable = new Table("Vechicles");
				vechiclesTable.addColumn(new Column("VehicleName",false, null));
						vechiclesTable.addColumn(new Column("VehicleTopSpeed", false, null));
		
//		
//		below is the relations
//

		tablesList.add(employeTable);
				tablesList.add(locationTable);
						tablesList.add(departmentTable);
								tablesList.add(productTable);
										tablesList.add(ordersTable);
												tablesList.add(vechiclesTable);
//		
//		develop logic to detect relation between tables
//		Refer the image for better understanding of the relationship
// --------------------------------------------------------------------------------------------------------------
//		example: 
// --------------------------------------------------------------------------------------------------------------
//		Input: Vechicles,VehicleName to Employee,EmployeeName
//      	Ouput:      
//			 Shortest path:
//		         Output --> Vechicles --> Employee(VehicleName,AssiagnedVehicle)
//		
//		         All possible path
//		 		 Output --> Vechicles --> Employee(VehicleName,AssiagnedVehicle)
//               Output --> Employee --> Vechicles(AssiagnedVehicle,VehicleName)
// --------------------------------------------------------------------------------------------------------------
//		Input: Department,DepartmentName to Orders,OrderProduct
//      	Ouput:      
//			 Shortest path:
//		         Output --> Department --> Orders(DepartmentHead,OrderBy)
//		
//		         All possible path
//				 Output --> Department --> Orders(DepartmentHead,OrderBy)
//				 Output --> Order --> Department(OrderBy,DepartmentHead)
//		 		 Output --> Department --> Employee(DepartmentHead,EmployeeName) --> Orders(EmployeeName,OrderBy)
//               Output --> Orders --> Employee(OrderBy,EmployeeName) --> Department(EmployeeName,DepartmentHead)
//               Output --> Department --> Employee(DepartmentHead,EmployeeName) --> Location(EmployeeName,LocationAdmin) --> Orders(LocationAdmin,OrderBy)
//      	 	 Output --> Orders --> Location(OrderBy,LocationAdmin) --> Employee(LocationAdmin,EmployeeName) --> Department(EmployeeName,DepartmentHead)
//               Output --> Department --> Location(DepartmentHead,LocationAdmin) --> Orders(LocationAdmin,OrderBy)
//      	 	 Output --> Orders --> Location(OrderBy,LocationAdmin) --> Department(LocationAdmin,DepartmentHead)
// --------------------------------------------------------------------------------------------------------------
//		Input: Product,ProductName to Vechicles,VehicleName
//      	Ouput:      
//			 Shortest path:
//		         Output --> Product --> Orders(ProductName,OrderProduct) --> Employee(OrderBy,EmployeeName) --> Vehicles(AssiagnedVehicle,VehicleName)
//		
//		         All possible path
//		         Output --> Product --> Orders(ProductName,OrderProduct) --> Employee(OrderBy,EmployeeName) --> Vehicles(AssiagnedVehicle,VehicleName)
//               Output --> Vehicles --> Employee(VehicleName,AssiagnedVehicle) --> Orders(EmployeeName,OrderBy) --> Product(OrderProduct,ProductName)
//               Output --> Product --> Orders(ProductName,OrderProduct) --> Location(OrderBy,LocationAdmin) --> Employee(LocationAdmin,EmployeeName) --> Vehicles(AssiagnedVehicle,VehicleName)
//               Output --> Vehicles --> Employee(VehicleName,AssiagnedVehicle) --> Location(LocationAdmin,LocationName) --> Orders(LocationAdmin,OrderBy) --> Product(OrderProduct,ProductName)
//               Output --> Product --> Orders(ProductName,OrderProduct) --> Department(OrderBy,DepartmentHead) --> Employee(DepartmentHead,EmployeeName) --> Vehicles(AssiagnedVehicle,VehicleName)
//               Output --> Vehicles --> Employee(VehicleName,AssiagnedVehicle) --> Department(EmployeeName,DepartmentHead) --> Orders(DepartmentHead,OrderBy) --> Product(OrderProduct,ProductName)
// -------------------------------------------------------------------------------------------------------------- 
//		Note: 
//		1. We need to execute this logic for N(say 100) tables and finding the relationship path should be in shortest time
//		2. We need both outputs
//			A. Shortest path
//			B. All possible paths
//		3. Two foreign key are inter related if they have same Primary key (for example: Location Admin, Dept Head and Order By are related)
//		4. Please refer the image for better understanding
//
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the source: ");
		String src = sc.nextLine();
		System.out.println("Enter the target: ");
		String tgt = sc.nextLine();
		Map<String, String> p2fRelations = getP2FRelations(tablesList);
		Set<String> possiblePaths = new LinkedHashSet<>();
		// source to target
		populateNextNodes(src, tgt, p2fRelations, possiblePaths);
		// target to source
		populateNextNodes(tgt, src, p2fRelations, possiblePaths);
		displayOutput(possiblePaths);
		sc.close();
	}

	private static Set<String> populateNextNodes(String path, String tgt, Map<String, String> p2fRelations,
			Set<String> nextNodes) {
		String[] pathArr = path.split("-->");
		String latestNode = pathArr[pathArr.length - 1];
		p2fRelations.forEach((pk, fk) -> {
			String latestNodeTable = latestNode.split(",")[0];
			String pkTable = pk.split(",")[0];
			String[] fkTables = fk.split(";");
			Set<String> tmpNodes = new LinkedHashSet<>();
			boolean tmpNodesCanBeAdded = false;
			// I. Handle fk to pk relation
			for (int i = 0; i < fkTables.length; i++) {
				if (fkTables[i].split(",")[0].equals(latestNodeTable)) { // compare fk with startNode
					tmpNodesCanBeAdded = true;
					if (!path.contains(pkTable)) { // check if path already contains pk
						if (pkTable.equals(tgt.split(",")[0])) { // check if we arrived at endNode
							nextNodes.add(path + "-->" + pk);
						} else {
							populateNextNodes(path + "-->" + pk, tgt, p2fRelations, nextNodes);
						}
					}
				} else {
					tmpNodes.add(fkTables[i]);
				}
			}
			// II. Handle interrelation
			if (tmpNodesCanBeAdded) {
				for (String tmpNode : tmpNodes) {
					String tmpNodeTable = tmpNode.split(",")[0];
					if (!path.contains(tmpNodeTable)) {
						if (tmpNodeTable.equals(tgt.split(",")[0])) {
							nextNodes.add(path + "-->" + tmpNode);
						} else {
							populateNextNodes(path + "-->" + tmpNode, tgt, p2fRelations, nextNodes);
						}
					}
				}
			} // III. Handle pk to fk relations
			else if (pkTable.equals(latestNodeTable)) {
				for (int i = 0; i < fkTables.length; i++) {
					if (!path.contains(fkTables[i].split(",")[0])) {
						if (fkTables[i].split(",")[0].equals(tgt.split(",")[0])) {
							nextNodes.add(path + "-->" + fkTables[i]);
						} else {
							populateNextNodes(path + "-->" + fkTables[i], tgt, p2fRelations, nextNodes);
						}
					}
				}
			}
		});
		return nextNodes;
	}

	private static Map<String, String> getP2FRelations(ArrayList<Table> tablesList) {
		Map<String, String> p2fRelations = new HashMap<>();
		for (Table tab : tablesList) {
			for (Column col : tab.tableColumns) {
				if (col.isForeignKey) {
					p2fRelations.compute(
							col.ForeignKeyRelation.foreignTable + "," + col.ForeignKeyRelation.foreignTableColumn,
							(pk, fk) -> {
								return (fk == null) ? (tab.tableName + "," + col.columnName)
										: (fk + ";" + tab.tableName + "," + col.columnName);
							});
				}
			}
		}
		return p2fRelations;
	}

	private static void displayOutput(Set<String> possiblePaths) {
		StringBuilder shortestPath = new StringBuilder();
		System.out.println("Shortest path:");
		for (String p : possiblePaths) {
			if (shortestPath.length() == 0) {
				shortestPath.append(p);
			} else if (p.split("-->").length < shortestPath.toString().split("-->").length) {
				shortestPath.setLength(0);
				shortestPath.append(p);
			}
		}
		System.out.println("Output --> " + shortestPath);
		System.out.println("All possible path:");
		for (String p : possiblePaths) {
			System.out.println("Output --> " + p);
		}
	}

	static class ForeignKeyRelation {
		String foreignTable;
		String foreignTableColumn;

		public ForeignKeyRelation() {
			// TODO Auto-generated constructor stub
		}

		public ForeignKeyRelation(String foreignTable, String foreignTableColumn) {
			this.foreignTable = foreignTable;
			this.foreignTableColumn = foreignTableColumn;
		}

	}

	static class Column {
		String columnName;
		boolean isForeignKey;
		ForeignKeyRelation ForeignKeyRelation;

		public Column() {
			// TODO Auto-generated constructor stub
		}

		public Column(String columnName, boolean isForeignKey, ForeignKeyRelation ForeignKeyRelation) {
			this.columnName = columnName;
			this.isForeignKey = isForeignKey;
			this.ForeignKeyRelation = ForeignKeyRelation;
		}

	}

	static class Table {
		String tableName;
		ArrayList<Column> tableColumns = new ArrayList<Column>();

		public Table() {
		}

		public Table(String tableName) {
			this.tableName = tableName;
		}

		public Table addColumn(Column column) {
			tableColumns.add(column);
			return this;
		}

	}

}
