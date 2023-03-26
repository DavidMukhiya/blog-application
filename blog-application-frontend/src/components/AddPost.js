import React, { useEffect, useRef, useState } from "react";
import {
  Button,
  Card,
  CardBody,
  Container,
  Form,
  Input,
  Label,
} from "reactstrap";
import { loadAllCategories } from "../services/category-service";
import JoditEditor from "jodit-react";
import { createPost as doCreatePost} from "../services/post-service";
import { getCurrentUserDetail } from "../auth";

const AddPost = () => {
  const editor = useRef(null);
  // const [content, setContent] = useState('')
  const [categories, setCategories] = useState([]);
  const [user, setUser] = useState(undefined)

  const [post, setPost] = useState({
    title: "",
    content: "",
    categoryId: "",
  });

  // const config = {
  //   placeholder:"Start typing..."
  // }

  useEffect(() => {
    
    setUser(getCurrentUserDetail())
    loadAllCategories()
      .then((data) => {
        console.log(data);
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  //field changed function
  const fieldChanged = (event) => {
    setPost({ ...post, [event.target.name]: event.target.value });
  };

  const contentFieldChanged = (data) => {
    setPost({ ...post, content: data });
  };

  //create post function
  const createPost = (event) => {
    event.preventDefault();
    // console.log("form submitted")
    console.log(post);
    if(post.title.trim()===''){
      alert("post title is required !!")
      return
    }
    if(post.content.trim()===''){
      alert("post content is required !!")
      return
    }
    if(post.categoryId===''){
      alert("select some category !!")
      return
    }

    //submit the form on server
    post['userId']=user.id
    doCreatePost(post).then(data=>{
      alert("post created ")
      console.log(post)
    }).catch((error)=>{
      alert("error")
      console.log(error)
    })
  };
  return (
    <div className="wrapper">
      <Card className="shadow-sm border-0 mt-2">
      {/* {JSON.stringify(post)} */}
        <CardBody>
          <h3>What's going in your mind</h3>
          <Form onSubmit={createPost}>
            <div className="my-3">
              <Label for="title">Post title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
                name="title"
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
                onChange={contentFieldChanged}
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
                defaultValue={0}
              >
              <option disabled value={0}>--Select Category--</option>
                {categories.map((category) => (
                  <option key={category.categoryId}>
                    {category.categoryTitle}
                  </option>
                ))}
              </Input>
            </div>
            <Container className="text-center">
              <Button type="submit" className="rounded-0" color="primary">
                Create Post
              </Button>
              <Button className="rounded-0 ms-2" color="danger">
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
