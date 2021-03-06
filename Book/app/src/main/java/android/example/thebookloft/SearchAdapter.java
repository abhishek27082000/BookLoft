package android.example.thebookloft;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.BooksViewHolder4>  {
    private Context context = null;
    private String url = null;
    //    private BooksModifier booksModifier;
    private static ArrayList<Book> shelf = new ArrayList<Book>();
    private int ListPosition = 0;
//    public interface BooksModifier{
    //     public void onBookTouched(int Position);}
 private View ClickedView;
static Book MoreDetails;
 public SearchAdapter(Context context, ArrayList<Book> shelf, int ListPosition){
        this.context = context;
        SearchAdapter.shelf = shelf;
        //this.shelf =  new ArrayList<ArrayList<Book>>(shelf);
        this.ListPosition = ListPosition;
    }
    //   public void setBooksModifier(BooksModifier booksModifier){
    //     this.booksModifier = booksModifier;}


    @NonNull
    @Override
    public BooksViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.book_for_results, parent, false);

        return new BooksViewHolder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder4 holder, int position) {
        Log.v("Notice","onBindViewHolder");
        try{
             MoreDetails = shelf.get(position);

            Log.v("Notice","Search Results Inflated");
            Picasso.with(context)
                    .load(MoreDetails.getBookImage())
                    .into(holder.bookPic);
            holder.title.setText(MoreDetails.getTitle());
            //holder.bookPic.setImageResource(R.drawable.ic_launcher_background);
            //          holder.BookCost.setText("$0");
            holder.BookRating.setRating(new Random().nextInt(6));


        }
        catch(IndexOutOfBoundsException e){
            StackTraceElement[] stackTraceElements = new StackTraceElement[0];
            e.setStackTrace(stackTraceElements);}
    }

    @Override
    public int getItemCount() {
        Log.v("Check ", ""+shelf.size());
         return shelf.size(); }


    public Book getItem(){
        return MoreDetails;
    }



    public static class BooksViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView bookPic;
        TextView title;
        RatingBar BookRating;
        RecyclerView ResultView;
        GridLayoutManager layoutManager;
        //TextView BookCost;


        public BooksViewHolder4(@NonNull View itemView) {
            super(itemView);

            bookPic = (ImageView) itemView.findViewById(R.id.Real_pic);
            title = (TextView) itemView.findViewById(R.id.Title_Book);
            BookRating = (RatingBar) itemView.findViewById(R.id.BookRating);
            ResultView = (RecyclerView) itemView.findViewById(R.id.Search_Results);
            //  BookCost = (TextView) itemView.findViewById(R.id.BookCost);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Log.i("Check ", "Book Clicked");
            int itemPosition = getLayoutPosition();
            Book item = shelf.get(itemPosition);
            Context context = v.getContext();
            Intent WebsiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getWebLink()));
            context.startActivity(WebsiteIntent);
        }
 }
    public void clear() {
        shelf.clear();
    }
    public void addAll(ArrayList<Book> data){
        shelf = data;
        notifyDataSetChanged();
    }
    public void setData(ArrayList<Book> shelf) {
        SearchAdapter.shelf = shelf;
        notifyDataSetChanged();
    }
    public void increase(){
        ListPosition++;
    }




}
