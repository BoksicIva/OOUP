
def mymax(iterable, key=lambda x :x):
    # incijaliziraj maksimalni element i maksimalni ključ
    max_x =max_key =None

    # obiđi sve elemente
    for x in iterable:
        # ako je key(x) najveći -> ažuriraj max_x i max_key
        if max_x is None or key(x) > max_key:
            max_x = x
            max_key = key(x)


            # vrati rezultat
    return max_x

myset = {"iva", "piva", "sljiva", "gljiva"}
maxset =mymax(myset )
print(maxset) #sljiva

D = {'burek': 8, 'buhtla': 5}
maxD=mymax(D,D.get)
print(maxD) #burek

maxint = mymax([1, 3, 5, 7, 4, 6, 9, 2, 0])
print(maxint) #9

maxchar = mymax("Suncana strana ulice")
print(maxchar) #u

maxstring = mymax([  "Gle", "malu", "vocku", "poslije", "kise", "Puna", "je", "kapi", "pa", "ih", "njise"])
print(maxstring) #vocku

maxstring2 = mymax([  "Gle", "malu", "vocku", "poslije", "kise", "Puna", "je", "kapi", "pa", "ih", "njise"], len)
print(maxstring2) #poslije

lista_ntorki=[("Iva","Boksic"),("Erna","Saric"),("Mladen","Dzida"),("Ana","Brnic"),("Jan","Rocek")]
maxntorka=mymax(lista_ntorki,lambda x: x[1])
print(maxntorka) #Erna





# # napravi bezimenu funkciju i poveži je s imenom f
# f = lambda x: 2*x+3

# # primijeni bezimenu funkciju
# f(3) # 9


