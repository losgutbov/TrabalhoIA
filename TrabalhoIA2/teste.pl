homem(lima).
homem(hamilton).
mulher(ana).
mulher(maria).
marido(lima, maria).
casado(hamilton, ana).

pai(lima, rodrigo).
pai(lima, teresa).
pai(rodrigo, marta).
pai(borge,lima).
pai(borge,hamilton).

mae(maria, rodrigo).
mae(maria, teresa).
mae(rosa, marta).
mae(paula, lima).
mae(paula, hamilton).
mae(joana, rosa).

filho(X, Y) :- pai(Y,X).

irmao(X,Y) :- pai(P,X),pai(P,Y), X\=Y.

tio(X,Y) :- pai(P,X),pai(P,Z),pai(Z,Y), X\=Z.

avô(X,Y) :- pai(X,Z),pai(Z,Y).

avó(X,Y) :- mae(X,Z),mae(Z,Y).