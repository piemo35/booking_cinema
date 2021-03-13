Programma java socket multi thread che gestisce le prenotazioni di una sala cinema da remoto.

Scopo dell'applicazione è gestire la prenotazione/acquisto da remoto di biglietti di una sala cinema.
La sala ha 150 posti a sedere.
Si suppone che il sistema gestisca la prenotazione dei biglietti per un solo spettacolo. A esempio, il sistema consente la prenotazione dei posti per il film "Via Col Vento", in programmazione Sabato 27 febbraio 2021.
Le prenotazioni sono possibili, fin tanto che rimangono posti a disposizione che non siano già stati prenotati da qualche altri cliente, fino a mezz'ora prima dello spettacolo, ovvero fino alle 19:30. Dopo le 19:30 le prenotazioni saranno chiuse.
Lo spettacolo inizia alle 20:00. Prima delle 20:00, chi ha prenotato deve presentarsi alla cassa e ritirare il biglietto. Non c'è da gestire il pagamento del biglietto, perché si suppone che chi prenota faccia, contestualmente, anche l'acquisto e il pagamento a distanza.

I 150 posti sono distribuiti su 10 file. Chi accede al sistema di prenotazione deve poter chiedere al sistema stesso di conoscere lo stato di prenotazione di tutti i posti.
A esempio, se il cliente chiede POSTI_DISPONIBILI, il sistema gli risponde con lo stato di tutti i posti e il CLIENTE deve vedere a schermo una rappresentazione L/O (L = libero, O = Occupato). A esempio (10 file da 15 posti ciascuna):

1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 (POSTO)
01) L L L O O L L L L L L O O O O
02) L L L O O L L L L L L O L L L
03) L L L O O L L L L L L O O L O
04) L L L O O L L L L L L O O O O
05) L L L O O L L L L L L O L O O
06) L L L O O L L L L L L O O O O
07) L L L O O L L L L L L O O O O
08) L L L O O L L L L L L O O L O
09) L L L O O L L L L L L O O O O
10) L L L O O L L OO L L O O O O

L'utente deve poi chiedere di prenotare 1 posto, specificandone le coordinate (numero di fila, numero di posto nella fila).

Il sistema:
- riceve la richiesta
- verifica se il posto è libero
- se è libero lo impegna con il nome, cognome, numero di cellulare e poi risponde all'utente confermando il buon esito dell'operazione: il posto richiesto è suo e di nessun altro utente
- se il posto non è libero, il sistema deve comunicarlo all'utente il quale avrà la possibilità di chiedere la prenotazione di un altro posto oppure abbandonare l'operazione, rinunciando a prenotare un posto qualsiasi
- se non ci sono più posti disponibili, il sistema deve comunicarlo all'utente

Si consiglia di usare due matrici. Una per tener traccia della disponibilità dei posti. L'altra, gemella e speculare, per tener traccia dei dati di anagrafica dei clienti che abbiano prenotato i posti.
