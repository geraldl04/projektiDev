# FSEmart

FSEmart qendron per: **Fast, Secure, Excellent Product Ordering System**

Nje sistem i ngjashem me Temu per menaxhimin e produkteve, shitjeve, etc.

---

## Pershkrim i Projektit

Si fillim userat jane krijuar per arsye demonstrative ne kete hap te projektimit.  
Produkti dhe Kategoria jane me kryesore.

- Produkti do kete nje Kategori.
- Kategoria mund te kete disa produkte.

Kete kam implementuar sot duke pasur kujdes thirrjen e pafundme.

---

## Funksionalitete

### Shtimi i nje produkti

Kam shtuar dhe nje tjeter me perpara.

![img.png](fsemart/src/main/resources/img.png)

### Marrja e nje kategorie qe liston njekohesisht dhe produktet qe ka

![img_1.png](fsemart/src/main/resources/img_1.png)

---


### Te testojme autentikim qe shtova dhe te jap outputet 

Kam bere qe nje user i thjeshte me rol user te mos mund te shikoje gjith userat por admini po dhe e njejta logjike eshte
ndjekur per te tjerat gjithashtu : 

Tregon qe eshte forbidden 
![img.png](img.png) 

Ndersa admini i shfaq  : 
![img_1.png](img_1.png)


### Te testojme 2 nga validimet 

Kur tenton te fusesh nje user por pa username 
![img_2.png](fsemart/src/main/resources/templates/img_2.png)

Kur tenton te fusesh nje email qe tashme ekzsiton
![img_3.png](fsemart/src/main/resources/templates/img_3.png)

![img_4.png](fsemart/src/main/resources/templates/img_4.png)


# Ndryshimet e bera sot

Dokument me ndryshimet funksionale dhe teknike te bera sot ne projekt.

---

## Ndryshime te entiteti Produkt

- Ne entitetin `Product` eshte shtuar nje reference ndaj `userSeller`.
- Kur nje user ben kerkese `GET` per produktet, i shfaqen edhe detajet e shitesit.

### Zgjidhja:
Per te kufizuar dhe kontrolluar cfare i shfaqet user-it ne varesi te rolit, jane krijuar dy DTO:

- `ProductUserDto`
- `ProductAdminDto`

---

## Ilustrime
SI admin : 
![img_2.png](img_2.png)
SI user : 
![img_3.png](img_3.png)


## Ndryshime te Kategoria

- Produkti ben pjese ne nje kategori.
- Ne momentin qe shfaqen produktet, shfaqen edhe detajet e kategorise .

### Zgjidhja:
Per kete arsye jane perdorur dy DTO:

- `CategoryUserDto`
- `CategoryAdminDto`

---
## Ilustrime

Si user : 
![img_4.png](img_4.png)

Si admin : 
![img_5.png](img_5.png)

## Ndryshime te Porosia

- Kur nje user ose admin shikon nje porosi, per shkak te nesting dalin te gjitha detajet e produktit, shitesit etj.

### Zgjidhja:
Per te kufizuar keto te dhena, jane perdorur DTO specifike per orderat .


## Ndryshime te orderi
Useri nuk duhet te lejohet te bej place order per nje id qe nuk eshte e vetmja , per demonstrim useri mund te beje porosi
vetem per veten ndersa admini per te gjithe

SI user qe tenton te shtoje te nje user me id tjeter : 
![img_7.png](img_7.png)
![img_8.png](img_8.png)

Useri  qe shton te id-ja e vet : 
![img_9.png](img_9.png)

Admini qe mund te shtoje kudo : 
![img_6.png](img_6.png)


## Shenim

Komentet qe kam bere pergjate file-ve jane per mua, qe ti perseris e ti mbaj mend, jo per shpjegim kodi.

