
document.addEventListener('deviceready', onDeviceReady, false);

//var db = null;
var db = window.openDatabase('PropertyTable', '1.0', 'Property Table', 5 * 1024 * 1024);

function onDeviceReady() {

    db.transaction(createTable);

    document.getElementById("saveButton").addEventListener("click", add);
    displayAll();

    document.getElementById("editButton").addEventListener("click", editRow);

    document.getElementById("deleteButton").addEventListener("click", deleteRow);
    document.getElementById("deleteAllButton").addEventListener("click", deleteAllRow);

}



function add() {
    db.transaction(function (tx)
        {
            var name = document.getElementById("name").value;
            var protype = document.getElementById("protype").options[document.getElementById("protype").selectedIndex].text;
            var leasetype = document.getElementById("leasetype").options[document.getElementById("leasetype").selectedIndex].text;
            var bednum = document.getElementById("bednum").options[document.getElementById("bednum").selectedIndex].text;
            var bathnum = document.getElementById("bathnum").options[document.getElementById("bathnum").selectedIndex].text;
            var size = document.getElementById("size").value;
            var price = document.getElementById("price").value;
            var garden = document.getElementById("garden").options[document.getElementById("garden").selectedIndex].text;
            var drive = document.getElementById("drive").options[document.getElementById("drive").selectedIndex].text;
            var local = document.getElementById("local").value;
            var description = document.getElementById("description").value;



            if (name == "" || protype == "Property Type: (REQUIRED)" || leasetype == "Lease Type: (REQUIRED)" || bednum == "Number of Bedroom: (REQUIRED)" || bathnum == "Number of Bathroom: (REQUIRED)" || size == "" || price == "" || garden == "Garden?: (REQUIRED)" || drive == "Drive?: (REQUIRED)" ){
            alert("Complete Required Field!");
            }

            else{
            var result = confirm('Property Name: '+name+'\nProperty Type: '+protype+'\nLease Type: '+leasetype+'\nBedroom Number: '+bednum+'\nBathroom Number: '+bathnum+'\nProperty Size: '+size+'\nProperty Price: '+price+'\nGarden?: '+garden+'\nDriveway?: '+drive+'\nLocal Amenities: '+local+'\nDescription: '+description);
             if(result===true)
               {

                tx.executeSql('INSERT INTO Property (name, protype, leasetype, bednum, bathnum, size, price, garden, drive, local, description) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',[name, protype, leasetype, bednum, bathnum, size, price, garden, drive, local, description],displayAll());
                 alert("Success!");
                 }


            }

        });

}


function createTable(tx){

   tx.executeSql('CREATE TABLE IF NOT EXISTS Property (id integer primary key autoincrement, name text, protype text, leasetype text, bednum text, bathnum text, size text, price text, garden text, drive text, local text, description text)');
   //alert("success!");

}


function deleteRow() {

    db.transaction(function (tx)
        {

            var editid = document.getElementById("editid").value;
            //alert(editid);

            tx.executeSql('DELETE FROM Property WHERE id = '+editid, displayAll());
            //tx.executeSql('DROP TABLE Property');

        });

}

function deleteAllRow() {

    db.transaction(function (tx)
        {

        var result = confirm('Are you sure?');

        if(result===true)
        {
            tx.executeSql('DELETE FROM Property', displayAll());
            //tx.executeSql('DROP TABLE Property');
        }



        });

}


function editRow() {

    db.transaction(function (tx)
        {
            var editid = document.getElementById("editid").value;
            var name = document.getElementById("name").value;
            var protype = document.getElementById("protype").options[document.getElementById("protype").selectedIndex].text;
            var leasetype = document.getElementById("leasetype").options[document.getElementById("leasetype").selectedIndex].text;
            var bednum = document.getElementById("bednum").options[document.getElementById("bednum").selectedIndex].text;
            var bathnum = document.getElementById("bathnum").options[document.getElementById("bathnum").selectedIndex].text;
            var size = document.getElementById("size").value;
            var price = document.getElementById("price").value;
            var garden = document.getElementById("garden").options[document.getElementById("garden").selectedIndex].text;
            var drive = document.getElementById("drive").options[document.getElementById("drive").selectedIndex].text;
            var local = document.getElementById("local").value;
            var description = document.getElementById("description").value;

            if (name == "" || protype == "Property Type: (REQUIRED)" || leasetype == "Lease Type: (REQUIRED)" || bednum == "Number of Bedroom: (REQUIRED)" || bathnum == "Number of Bathroom: (REQUIRED)" || size == "" || price == "" || garden == "Garden?: (REQUIRED)" || drive == "Drive?: (REQUIRED)" ){
                alert("Complete Required Field!");
                        }
            else{
              tx.executeSql("UPDATE Property SET name = '"+name+"', protype = '"+protype+"', leasetype = '"+leasetype+"', bednum = '"+bednum+"', bathnum = '"+bathnum+"', size = '"+size+"', price = '"+price+"', garden = '"+garden+"', drive = '"+drive+"', local = '"+local+"', description = '"+description+"' WHERE id = "+editid,displayAll());
             }
        });

}


function displayAll(){
    db.transaction(function (tx){
        tx.executeSql('SELECT * from Property', [], function(tx, results) {
            var n = results.rows.length;
            var s = '<table cellpadding="2" cellspacing="2" border="1">';
            s += '<tr> <th>ID</th> <th>Name</th> <th>Property Type</th> <th>Lease Type</th> <th>Bedroom Number</th> <th>Bathroom Number</th> <th>Property Size</th> <th>Property Price</th> <th>Garden (YES or NO)</th> <th>Driveway (YES or NO)</th> <th>Local Amenities</th> <th>Description</th> </tr>';
            for(var i=0; i<n; i++) {
                var Property = results.rows.item(i);
                s += '<tr>';

                s +=  '<td>' + Property.id + '</td>';

                s +=  '<td>' + Property.name + '</td>';
                s +=  '<td>' + Property.protype + '</td>';
                s +=  '<td>' + Property.leasetype + '</td>';
                s +=  '<td>' + Property.bednum + '</td>';
                s +=  '<td>' + Property.bathnum + '</td>';
                s +=  '<td>' + Property.size + '</td>';
                s +=  '<td>' + Property.price + '</td>';
                s +=  '<td>' + Property.garden + '</td>';
                s +=  '<td>' + Property.drive + '</td>';
                s +=  '<td>' + Property.local + '</td>';
                s +=  '<td>' + Property.description + '</td>';

                s += '</tr>';
            }
            s += '</table>';
            document.getElementById('result').innerHTML = s;

        });
    });
}


