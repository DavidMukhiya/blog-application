import React, { useEffect, useRef, useState } from "react";
import { Button, Card, CardBody, Container, Form, Input, Label } from "reactstrap";
import { loadAllCategories } from "../services/category-service";
import JoditEditor from "jodit-react";

const AddPost = () => {

  const editor = useRef(null)
  // const [content, setContent] = useState('')
  const [categories, setCategories] = useState([])

  const [post, setPost] = useState({
    title:'',
    content:'',
    categoryId:''
  })

  // const config = {
  //   placeholder:"Start typing..."
  // }

  useEffect(()=>{
    loadAllCategories().then((data)=>{
        console.log(data)
        setCategories(data)
    }).catch(error=>{
        console.log(error)
    })
  },[])

  //field changed function
  const fieldChanged = (event)=>{
    setPost({...post, [event.target.name]:event.target.value})
  }

  const contentFieldChanged = (data)=>{
    setPost({...post, 'content':data})
  }


  //create post function
  const createPost = (event) =>{
    event.preventDefault();
    // console.log("form submitted")
    console.log(post)
  }
  return (
    <div className="wrapper">
      <Card className="shadow-sm border-0 mt-2" >
        <CardBody>
          <h3>What's going in your mind</h3>
          <Form onSubmit={createPost} >
            <div className="my-3">
              <Label for="title">Post title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
                name="username"
                onChange={fieldChanged}
              />
            </div>
            <div className="my-3">
              <Label for="content">Post content</Label>
              {/* <Input
                type="textarea"
                id="content"
                placeholder="Enter here"
                className="rounded-0"
                style={{ height: "300px" }}
              /> */}

              <JoditEditor
                ref={editor}
                value={post.content}
                onChange={(newContent)=>contentFieldChanged}
              />
            </div>
            <div className="my-3">
              <Label for="category">Post category</Label>
              <Input
                type="select"
                id="category"
                placeholder="Enter here"
                className="rounded-0"
                name="categoryId"
                onChange={fieldChanged}
              >
                {
                  categories.map((category)=>(
                    <option key={category.categoryId}>
                      {category.categoryTitle}
                    </option>
                  ))
                }
              </Input>
            </div>
            <Container className="text-center">
                <Button type="submit" className="rounded-0" color="primary">
                    Create Post
                </Button>
                <Button  className="rounded-0 ms-2" color="danger">
                    Reset Content
                </Button>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};

export default AddPost;
