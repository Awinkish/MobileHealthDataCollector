package com.example.khalif.a514app.Utils;

/**
 * Created by Khalif on 7/11/2017.
 */
public interface I_fragmentlistener<T,Y, P> {
    public void onData(T t);
    public void ansQuestion(Y y);
    public void ansQuestionPregnant(P p);
}
