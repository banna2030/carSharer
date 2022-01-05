<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Fahrt Erstellen</title>
</head>
<body>
    <!--Header-->
    <header class="mainh"> <h1>Fahrt erstellen</h1></header>
    <!--End Header-->
    <!--distinations-->
    <form class="regst">
        <div class="dist">
            <label for="from">Von:</label>
            <input name="from"type="text" id="from"><br><br>

            <label for="to">Bis:</label>
            <input name="to" type="text" id="to"><br><br>
        </div>
      
        <div class="cap">
            <label for="capcity">Maximale Kapzität:</label>
            <input name="capacity" type="number" min="1" id="capcity"><br>
        </div>
        <div class="cos">
            <label for="cost">Fahrtkosten:</label>
            <input name="cost" type="number" min="1" id="cost"> €<br>
        </div>
        <div class="transporttype">
            <div>
                <label for="">Transportmittel:</label>
              <input type="radio" id="choice1"
               name="transport" value="Auto">
              <label for="choice1">Auto</label>
          
              <input type="radio" id="choice2"
               name="transport" value="Bus">
              <label for="choice2">Bus</label>
          
              <input type="radio" id="choice3"
               name="transport" value="Kleintransporter">
              <label for="choice3">Kleintransporter</label>
            </div>
        </div>
        <div class="time">
            <label for="Fahrtdatum">Fahrtdatum:</label>
            <input type="date" id="Fahrtdatum" name="trip-start" value="2022-02-12" min="2022-01-01" max="2022-12-31">
            <input type="time" id="Fahrtdatum" name="appt" value="12:00"min="00:00" max="24:00" >
        </div>
        <div class="description">
            <label for="Beschreibung">Beschreibung:</label>
            <input type="text" id="Beschreibung" name="description" >
        </div>

        <button class="create"type="submit">Erstellen</button>
    </form>
    

</body>
</html>