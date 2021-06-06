<?php

namespace App\Http\Controllers;
use App\Models\Book;
use \Illuminate\Http\Request;
use Illuminate\Database\Eloquent\ModelNotFoundException;

class BooksController extends Controller
{
public function index()
{ 
    return Book::all();
}

//menampilkan data byId
public function getId($id){
    $buku = Book::where('id', $id)->first();
        if ($buku) {
            return 
            response()->json([
                'message' => 'menampilkan buku by id',
                'data' => $buku ], 200);
            
        } else {
            return response()->json([
                'message' => 'buku tidak ada',
            ], 404);
        }
    }

//menampilkan data byJudul
public function getJudul($judul){

    $judul = urldecode($judul);
    
    $buku = Book::where('judul', 'LIKE', '%'.$judul.'%')->get();
        if ($buku) {
            return 
            response()->json([
                'message' => 'tampil buku by judul',
                'data' => $buku ], 200);
            
        } else {
            return response()->json([
                'message' => 'buku tidak ada',
            ], 404);
        }
    }

//membuat data buku
public function createBuku (Request $request){
    $this->validate($request, [ 
        'judul' => 'required',
        'penulis' => 'required',
        'kategori' => 'required',
        'stock' => 'required'
    ]);

    $buku = Book::create(
        $request->only(['judul', 'penulis', 'kategori', 'stock'])
    );

    return response()->json([
       'updated'=>true,
       'data'=>$buku
   ], 201);
    }

//mengupdate data buku
public function updateBuku(Request $request, $id){
        try {
            $buku = Book::findOrFail ($id);
        } catch (ModelNotFoundException $e){
            return response()->json([
                'message' => 'buku tidak ada'
            ], 404);
        }
    
    $buku->fill (
        $request->only(['judul', 'penulis', 'kategori', 'stock'])
    );

    $buku->save();

    return response()->json([
        'created' => true,
        'data' => $buku
    ], 200);
    }

//menghapus data buku byId
public function deletebyId($id){
        try{
            $buku = Book::findOrFail($id);
        } catch (ModelNotFoundException $e){
            return response()->json([
                'error' => [
                    'message' => 'buku gagal dihapus'
                ]
                ], 404);
        }

        $buku->delete();

        return response()->json([
            'buku berhasil di hapus' => true
        ], 200);
    }
}
