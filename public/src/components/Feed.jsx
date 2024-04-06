import "../styles/Feed.css"

const Feed = () => {
    return (
        <div className="table">
            <table>
                <thead>
                    <tr>
                        <th>Food</th>
                        <th>Saturation</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Alfreds Futterkiste</td>
                        <td>Maria Anders</td>
                        <td>Germany</td>
                    </tr>
                    <tr>
                        <td>Centro comercial Moctezuma</td>
                        <td>Francisco Chang</td>
                        <td>Mexico</td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default Feed;