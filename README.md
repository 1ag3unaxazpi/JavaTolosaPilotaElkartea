# Tolosa Pilota Kirol Elkartea - Java aplikazioa

- [Tolosa Pilota Kirol Elkartea - Java aplikazioa](#tolosa-pilota-kirol-elkartea-java-aplikazioa)
    - [Interfazearen azalpena](#interfazearen-azalpena)
        - [Botoiak](#botoiak)
        - [Desplegablea](#desplegablea)
    - [Klase egitura orokorra](klase-egitura-orokorra)
        - [Klaseak](#klaseak)
        - [Metodoak](#metodoak)
        - [Atributuak](#atributuak)


## Interfazearen azalpena

![alt text](github_irudiak/interfazea.png)

### Botoiak

- **Fitxategia aukeratu**: botoia sakatzean, .csv fitxategia hautatzeko leihoa irekitzen da.

- **Bihurtu**: botoia sakatzean, .csv fitxategia .sql fitxategi bihurtzeko prozesua hasten da.

### Desplegablea

- **Erabiltzaileak/Lehiaketak**: erabiltzaileen edo lehiaketen fitxategi datuak bihurtzeko aukera.


## Klase egitura orokorra

Aplikazioaren klase, metodo eta atributuen azalpen orokorra.

### Klaseak

- **Nagusia**: .csv fitxategiak .sql-era bihurtzen dituen aplikazioa exekutatzen duen klasea.

- **Layout**: .csv fitxategiak .sql-era bihurtzen dituen aplikazio klasea.

- **DBOperazioak**: Datu-baseko operazio metodoak dituen klasea.

- **Erabiltzailea**: Erabiltzailea objektuaren klasea.

- **Lehiaketa**: Lehiaketa objektuaren klasea.

- **LayoutTest**: Layout klasearen metodoak testeatzeko klasea.

- **DBOperazioakTest**: DBOperazioak klaseko metodoak testeatzeko klasea.

- **AllTests**: Layout eta DBOperazioak klaseen metodoak testeatzeko klasea.

### Metodoak

- *Nagusia*
    - **main**: Aplikazioren sarrera nagusia. Programa hasten denean exekutatzen den metodoa da.
        - **run**: Interfaze grafikoa eraikiko da metoda exekutatzerakoan.

- *Layout*
    - **Layout**: Layout klasearen metodo eraikitzailea.
        - **actionPerformed**: botoia sakatzean ekintza bat egingo duen metodoa.
    - **fitxategiaSortu**: .sql fitxategia sortzen duen metodoa.
    - **bihurtu**: .csv fitxategia .sql fitxategi batean bihurtzen duen metodoa.

- *DBOperazioak*
    - **queryInsertErabiltzailea**: Erabiltzaile berri bat gehitzeko sortzen duen SQL kontsulta metodoa da.
    - **queryInsertLehiaketa**: Lehiaketa berri bat gehitzeko sortzen duen SQL kontsulta metodoa da.

- *Erabiltzailea*
    - **Erabiltzailea**: Erabiltzailea objektuaren eraikitzailea.

- *Lehiaketa*
    - **Lehiaketa**: Lehiaketa objektuaren eraikitzailea.

- *LayoutTest*
    - **testBihurtuErabiltzaileak**: Erabiltzaileen .csv fitxategia .sql fitxategira ondo bihurtzen duen test metodoa.
    - **testBihurtuLehiaketak**: Lehiaketen .csv fitxategia .sql fitxategira ondo bihurtzen duen test metodoa.

- *DBOperazioakTest*
    - **testQueryInsertErabiltzailea**: Erabiltzailea gehitzeko SQL kontsulta ondo sortzen duen test metodoa
    - **testQueryInsertLehiaketa**: Lehiaketa gehitzeko SQL kontsulta ondo sortzen duen test metodoa.

### Atributuak

- *Layout*
    - **frame**: Aplikazioaren lehioa.
    - **mainPanel**: Lehioan dagoen panel nagusia.
    - **topPanel**: Panel nagusiaren barruan dagoen goiko panela.
    - **centerPanel**: Panel nagusiaren barruan dagoen erdiko panela.
    - **comboBox**: Erabiltzaileak/Lehiaketak aukerak hautatzeko desplegablea
    - **selectLabel**: .csv fitxategia aukeratzeko botoiaren etiketa.
    - **selectButton**: .csv fitxategia aukeratzeko botoia.
    - **selectFileChooser**: .csv fitxategia aukeratzeko lehioa.
    - **destinationFileChooser**: .sql fitxategia gordetzeko lehioa.
    - **actionButton**: .csv fitxategia .sql fitxategi bihurtzeko botoia.

- *Erabiltzailea*
    - **username**: Erabiltzailearen username.
    - **pasahitza**: Erabiltzailearen pasahitza.
    - **izena**: Erabiltzailearen izena.
    - **abizenak**: Erabiltzailearen abizenak.
    - **aktibo**: Erabiltzailea aktibo dagoen edo ez (Aktibo: 1 / Ez aktibo: 0).
    - **email**: Erabiltzailearen email-a.
    - **helbidea**: Erabiltzailearen helbidea.
    - **telefonoa**: Erabiltzailearen telefonoa.
    - **administratzailea**: Erabiltzailea administratzailea den ala ez (Da: 1 / Ez da: 0).

- *Lehiaketa*
    - **kodea**: Lehiaketaren kodea.
    - **izena**: Lehiaketaren izena.
    - **kategoria**: Lehiaketaren kategoria.
    - **denboraldia**: Lehiaketaren denboraldia.
    - **hasiera_data**: Lehiaketaren hasiera-data.
    - **bukaera_data**: Lehiaketaren bukaera-data.